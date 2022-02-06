package com.sorokin.gifviewer.data.entity.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("description")
    val description: String,
    @SerialName("author")
    val author: String,
    @SerialName("gifURL")
    val gifUrl: String
)
