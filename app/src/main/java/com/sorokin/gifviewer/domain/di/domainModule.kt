package com.sorokin.gifviewer.domain.di

import com.sorokin.gifviewer.data.di.dataModule
import com.sorokin.gifviewer.domain.usecase.GetGifsOfSectionUseCase
import com.sorokin.gifviewer.domain.usecase.GetRandomGifUseCase
import kotlinx.serialization.ExperimentalSerializationApi
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

@ExperimentalSerializationApi
fun domainModule() = DI.Module("DomainModule") {
    importOnce(dataModule())

    bindSingleton {
        GetRandomGifUseCase(instance())
    }

    bindSingleton {
        GetGifsOfSectionUseCase(instance())
    }
}
