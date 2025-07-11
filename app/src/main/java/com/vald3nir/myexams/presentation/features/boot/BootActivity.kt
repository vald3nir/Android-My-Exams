package com.vald3nir.myexams.presentation.features.boot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.myexams.presentation.CustomActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class BootActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BootScreen(this@BootActivity)
        }
    }
}

internal fun Context.startBootActivity() {
    val intent = Intent(this, BootActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}