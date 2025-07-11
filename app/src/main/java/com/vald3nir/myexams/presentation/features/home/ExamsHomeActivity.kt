package com.vald3nir.myexams.presentation.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.myexams.presentation.CustomActivity
import com.vald3nir.myexams.presentation.features.home.navigator.HomeExamScreenNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ExamsHomeActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeExamScreenNavHost(this@ExamsHomeActivity)
        }
    }
}

fun Context.startExamsHomeActivity() {
    val intent = Intent(this, ExamsHomeActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}