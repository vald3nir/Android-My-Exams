package com.vald3nir.toolkit.helpers.utils

import java.util.Locale

fun String?.getLastXLetters(x: Int): String = if (this.isNullOrEmpty() || this.length < x) "" else this.takeLast(2)

fun String.limitToX(x: Int = 10): String = if (length > x) take(x) + "..." else this

fun String.cap(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}