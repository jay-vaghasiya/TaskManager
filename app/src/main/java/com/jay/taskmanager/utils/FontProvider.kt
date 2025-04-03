package com.jay.taskmanager.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import com.jay.taskmanager.R

object StrawFordFont {
    val FontFamily = FontFamily(
        Font(resId = R.font.strawford_black, weight = FontWeight.Normal),
        Font(resId = R.font.strawford_bold, weight = FontWeight.Bold),
        Font(resId = R.font.strawford_extra_light, weight = FontWeight.ExtraLight),
        Font(resId = R.font.strawford_light, weight = FontWeight.Light),
        Font(resId = R.font.strawford_medium, weight = FontWeight.Medium),
        Font(resId = R.font.strawford_thin, weight = FontWeight.Thin)
    )
}