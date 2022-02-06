package com.sorokin.gifviewer.data.mapper

import com.sorokin.gifviewer.data.entity.network.response.GifResponse
import com.sorokin.gifviewer.domain.entity.Gif

class GifMapper {

    fun getFromGifResponse(gifResponse: GifResponse): Gif = Gif(
        id = gifResponse.id,
        description = gifResponse.description,
        author = gifResponse.author,
        gifUrl = gifResponse.gifUrl.replace("http", "https")
    )
}