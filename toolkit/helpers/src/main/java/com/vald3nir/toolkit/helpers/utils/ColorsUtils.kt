package com.vald3nir.toolkit.helpers.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun getRandomColors(size: Int): List<Color> {
    return (1..size).map {
        Color(
            Random.nextFloat(), // Gera um valor aleatório para o componente Red (0-1)
            Random.nextFloat(), // Gera um valor aleatório para o componente Green (0-1)
            Random.nextFloat(), // Gera um valor aleatório para o componente Blue (0-1)
            1f // Canal Alpha (opacidade) fixo em 1 (completamente opaco)
        )
    }
}