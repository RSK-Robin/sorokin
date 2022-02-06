package com.sorokin.gifviewer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class GifsType(val sectionName: String):Parcelable {
    RANDOM("random"),
    HOT("hot"),
    LATEST("latest"),
    TOP("top")
}