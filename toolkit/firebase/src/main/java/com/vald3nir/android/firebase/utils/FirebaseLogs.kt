package com.vald3nir.android.firebase.utils

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

fun Exception.notifyLog() {
    this.printStackTrace()
    Firebase.crashlytics.recordException(this)
}

fun Throwable.notifyLog() {
    this.printStackTrace()
    Firebase.crashlytics.recordException(this)
}