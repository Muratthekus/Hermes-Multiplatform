package me.thekusch.hermes.android.intro.domain.model

import androidx.annotation.DrawableRes

data class IntroItem(
    @DrawableRes val image: Int,
    val text: String
)