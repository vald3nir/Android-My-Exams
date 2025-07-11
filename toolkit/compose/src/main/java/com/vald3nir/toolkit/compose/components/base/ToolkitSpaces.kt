package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val defaultSpace = 16.dp
val halfSpace = defaultSpace / 2
val minSpace = halfSpace / 2
val bigSpace = defaultSpace * 4

@Composable
fun DefaultSpaceHeight() = Spacer(modifier = Modifier.height(defaultSpace))

@Composable
fun DefaultSpaceWidth() = Spacer(modifier = Modifier.width(defaultSpace))

@Composable
fun HalfSpaceHeight() = Spacer(modifier = Modifier.height(halfSpace))

@Composable
fun HalfSpaceWidth() = Spacer(modifier = Modifier.width(halfSpace))

@Composable
fun BigSpaceHeight() = Spacer(modifier = Modifier.height(bigSpace))

@Composable
fun BigSpaceWidth() = Spacer(modifier = Modifier.width(bigSpace))

@Composable
fun MinSpaceHeight() = Spacer(modifier = Modifier.height(minSpace))

@Composable
fun MinSpaceWidth() = Spacer(modifier = Modifier.width(minSpace))



