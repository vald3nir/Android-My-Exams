package com.vald3nir.toolkit.helpers.utils.parses

fun String.monetaryToDouble(): Double = this.replace("R$", "").replace(".", "").replace(",", ".").trim().toDouble()
