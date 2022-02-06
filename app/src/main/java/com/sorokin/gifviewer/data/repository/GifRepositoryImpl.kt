package com.sorokin.gifviewer.data.repository

import com.sorokin.gifviewer.data.mapper.GifMapper
import com.sorokin.gifviewer.data.network.GifApi
import com.sorokin.gifviewer.domain.entity.Gif
import com.sorokin.gifviewer.domain.repository.GifRepository

class GifRepositoryImpl(
    private val gifMapper: GifMapper,
    private val gifApi: GifApi
) : GifRepository {

    override suspend fun getGifsBySection(section: String, page: Int): List<Gif> =
        gifApi.getGifsBySection(section, page).let { gifsListResponse ->
            gifsListResponse.result.map { gifMapper.getFromGifResponse(it) }
        }

    override suspend fun getRandomGif(): Gif =
        gifApi.getRandomGif().let { gifMapper.getFromGifResponse(it) }
}
