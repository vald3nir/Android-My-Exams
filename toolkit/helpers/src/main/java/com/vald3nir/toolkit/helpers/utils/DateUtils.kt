package com.vald3nir.toolkit.helpers.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun getSizeDaysInMonth(month: Int, year: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun Int.getMonthReduced(): String {
    val months = listOf(
        "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"
    )
    return if (this in 1..12) months[this - 1] else ""
}

fun String?.getMonthReduced(): String {
    return if (this.isNullOrEmpty()) "" else this.toInt().getMonthReduced()
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

fun String.getAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val birthday = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    return Period.between(birthday, today).years
}