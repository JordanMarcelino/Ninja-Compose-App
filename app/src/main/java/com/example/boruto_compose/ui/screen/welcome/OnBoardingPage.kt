package com.example.boruto_compose.ui.screen.welcome

import androidx.annotation.DrawableRes
import com.example.boruto_compose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about."
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your hero's power and  see how much are they strong comparing to others."
    )
}

val pages = listOf(
    OnBoardingPage.First,
    OnBoardingPage.Second,
    OnBoardingPage.Third,
)
