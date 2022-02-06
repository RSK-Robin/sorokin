package com.sorokin.gifviewer.data.entity.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifsListResponse(
    @SerialName("result")
    val result: List<GifResponse>
)
