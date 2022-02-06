package com.sorokin.gifviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sorokin.gifviewer.utils.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<MainActivity>()
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        finish()
    }
}
