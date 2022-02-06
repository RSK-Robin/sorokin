package com.sorokin.gifviewer.domain.usecase

import com.sorokin.gifviewer.domain.repository.GifRepository

class GetGifsOfSectionUseCase(private val gifRepository: GifRepository) {

    suspend operator fun invoke(section: String, page: Int) =
        gifRepository.getGifsBySection(section, page)
}
