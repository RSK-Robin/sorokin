package com.sorokin.gifviewer.domain.repository

import com.sorokin.gifviewer.domain.entity.Gif

interface GifRepository {

    suspend fun getGifsBySection(section: String, page: Int): List<Gif>
    suspend fun getRandomGif(): Gif
}
