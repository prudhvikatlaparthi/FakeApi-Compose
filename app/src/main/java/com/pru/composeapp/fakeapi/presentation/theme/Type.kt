package com.pru.composeapp.fakeapi.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.pru.composeapp.fakeapi.R

val OpenSans = FontFamily(
    Font(R.font.open_sans_regular),
    Font(R.font.opensans_light, FontWeight.Light),
    Font(R.font.open_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.open_sans_bold, FontWeight.Bold)
)
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = OpenSans
    ),
    displayMedium = TextStyle(
        fontFamily = OpenSans
    ),
    displaySmall = TextStyle(
        fontFamily = OpenSans
    ),
    headlineLarge = TextStyle(
        fontFamily = OpenSans
    ),
    headlineMedium = TextStyle(
        fontFamily = OpenSans
    ),
    headlineSmall = TextStyle(
        fontFamily = OpenSans
    ),
    titleLarge = TextStyle(
        fontFamily = OpenSans
    ),
    titleMedium = TextStyle(
        fontFamily = OpenSans
    ),
    titleSmall = TextStyle(
        fontFamily = OpenSans
    ),
    bodyLarge = TextStyle(
        fontFamily = OpenSans
    ),
    bodyMedium = TextStyle(
        fontFamily = OpenSans
    ),
    bodySmall = TextStyle(
        fontFamily = OpenSans
    ),
    labelLarge = TextStyle(
        fontFamily = OpenSans
    ),
    labelMedium = TextStyle(
        fontFamily = OpenSans
    ),
    labelSmall = TextStyle(
        fontFamily = OpenSans
    )
)