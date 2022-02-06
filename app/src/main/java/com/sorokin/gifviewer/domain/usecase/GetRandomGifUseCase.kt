package com.sorokin.gifviewer.domain.usecase

import com.sorokin.gifviewer.domain.repository.GifRepository

class GetRandomGifUseCase(private val gifRepository: GifRepository) {

    suspend operator fun invoke() = gifRepository.getRandomGif()
}
