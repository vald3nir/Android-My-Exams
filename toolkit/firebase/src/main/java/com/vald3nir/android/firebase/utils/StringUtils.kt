package com.vald3nir.android.firebase.utils

fun String.parseEmailToKey() = this.replace("@", "_").replace(".", "_")