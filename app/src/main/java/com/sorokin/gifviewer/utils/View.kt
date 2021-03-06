package com.sorokin.gifviewer.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.gif.GifDrawable

fun ImageView.loadGif(gifUrl: String) {
    Glide.with(this)
        .asGif()
        .load(gifUrl)
        .attachProgressDrawable(this.context)
        .into(this)
}

@SuppressLint("CheckResult")
private fun RequestBuilder<GifDrawable>.attachProgressDrawable(context: Context) = apply {
    val circularProgressDrawable = CircularProgressDrawable(context).apply {
        strokeWidth = 4f
        centerRadius = 45f
        start()
    }
    placeholder(circularProgressDrawable)
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}
