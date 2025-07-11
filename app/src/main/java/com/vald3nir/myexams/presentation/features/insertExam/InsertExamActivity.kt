package com.vald3nir.myexams.presentation.features.insertExam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.myexams.presentation.CustomActivity
import com.vald3nir.myexams.presentation.features.insertExam.navigator.InsertExamScreenNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class InsertExamActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsertExamScreenNavHost(this@InsertExamActivity)
        }
    }
}

fun Context.startInsertExamActivity() {
    startActivity(Intent(this, InsertExamActivity::class.java))
}