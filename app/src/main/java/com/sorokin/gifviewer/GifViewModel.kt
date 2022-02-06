package com.sorokin.gifviewer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sorokin.gifviewer.domain.entity.Gif
import com.sorokin.gifviewer.domain.usecase.GetGifsOfSectionUseCase
import com.sorokin.gifviewer.domain.usecase.GetRandomGifUseCase
import com.sorokin.gifviewer.utils.launchWithExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class GifViewModel(application: Application, private val sectionType: GifsType) :
    AndroidViewModel(application), DIAware {

    override val di by closestDI()
    private val getGifsOfSectionUseCase by instance<GetGifsOfSectionUseCase>()
    private val getRandomGifUseCase by instance<GetRandomGifUseCase>()

    private val statePrivate = MutableStateFlow<GifState>(GifState.Started())
    val state = statePrivate.asStateFlow()

    private val loadedGifs: MutableList<Gif> = mutableListOf()
    private var currentNumberOfGif = -1
    private var currentPageOfSection = -1

    init {
        loadFirstGif()
    }

    private fun loadFirstGif() {
        loadNextGif()
    }

    fun nextButtonClicked() {
        loadNextGif()
    }

    fun previouslyButtonClicked() {
        loadPreviouslyGif()
    }

    fun repeatButtonClicked() {
        repeatLoadGif()
    }

    private fun repeatLoadGif() {
        loadGifFromNetwork()
    }

    private fun loadNextGif() {
        currentNumberOfGif++
        if (isGifInCache(currentNumberOfGif)) {
            loadGifFromCache()
        } else {
            loadGifFromNetwork()
        }
    }

    private fun loadPreviouslyGif() {
        currentNumberOfGif--
        loadGifFromCache()
    }

    private fun isGifInCache(position: Int): Boolean =
        loadedGifs.getOrNull(position) != null

    private fun loadGifFromNetwork() {
        statePrivate.value = GifState.Loading(visibilityOfButtonPreviously = hasPreviouslyGif())
        viewModelScope.launchWithExceptionHandler(
            Dispatchers.IO,
            catchBlock = {
                statePrivate.value = GifState.Error(hasPreviouslyGif(), hasNextGif())
                false
            }
        ) {
            if (sectionType == GifsType.RANDOM) {
                getRandomGifUseCase().let { gif: Gif ->
                    loadedGifs.add(gif)
                    statePrivate.value = GifState.Success(gif, hasPreviouslyGif(), hasNextGif())
                }
            } else {
                getGifsOfSectionUseCase(
                    sectionType.sectionName,
                    ++currentPageOfSection
                ).let { list: List<Gif> ->
                    loadedGifs.addAll(list)
                    statePrivate.value =
                        GifState.Success(list.first(), hasPreviouslyGif(), hasNextGif())
                }
            }
        }
    }

    private fun loadGifFromCache() {
        statePrivate.value =
            GifState.Success(loadedGifs[currentNumberOfGif], hasPreviouslyGif(), hasNextGif())
    }

    private fun hasNextGif(): Boolean =
        isGifInCache(currentNumberOfGif + 1)

    private fun hasPreviouslyGif(): Boolean =
        currentNumberOfGif > 0
}
