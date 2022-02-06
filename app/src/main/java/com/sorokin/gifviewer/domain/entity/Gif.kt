package com.sorokin.gifviewer.domain.entity

data class Gif(
    val id: Int,
    val description: String,
    val author: String,
    val gifUrl: String
)
