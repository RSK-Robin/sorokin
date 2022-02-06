package com.sorokin.gifviewer

import com.sorokin.gifviewer.domain.entity.Gif

sealed class GifState {
    data class Started(val visibilityOfButtonPreviously: Boolean = false) : GifState()
    data class Loading(
        val visibilityOfButtonPreviously: Boolean,
        val visibilityOfButtonNext: Boolean = false
    ) : GifState()

    data class Error(
        val visibilityOfButtonPreviously: Boolean,
        val visibilityOfButtonNext: Boolean
    ) : GifState()

    data class Success(
        val gif: Gif,
        val visibilityOfButtonPreviously: Boolean,
        val visibilityOfButtonNext: Boolean = true
    ) : GifState()
}
