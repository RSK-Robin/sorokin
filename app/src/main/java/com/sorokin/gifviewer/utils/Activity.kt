package com.sorokin.gifviewer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
    startActivity(Intent(this, T::class.java).apply { putExtras(bundleOf(*params)) })