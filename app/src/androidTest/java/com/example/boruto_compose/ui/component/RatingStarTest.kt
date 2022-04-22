package com.example.boruto_compose.ui.component

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import org.junit.Rule
import org.junit.Test

class RatingStarTest{

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun ratingStar_givenZeroPointZeroRate_FiveEmptyStar() {
        composeRule.setContent {
            RatingStar(rating = 0.0)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }

    @Test
    fun ratingStar_givenZeroPointFiveRate_OneHalfFilledStar() {
        composeRule.setContent {
            RatingStar(rating = 0.5)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
    }

    @Test
    fun ratingStar_givenZeroPointSixRate_OneFilledStar() {
        composeRule.setContent {
            RatingStar(rating = 0.6)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(1)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
    }

    @Test
    fun ratingStar_givenFourPointZeroRate_FourFilledStar() {
        composeRule.setContent {
            RatingStar(rating = 4.0)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(4)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(1)
    }

    @Test
    fun ratingStar_givenFourPointFiveRate_OneHalfFilledStar() {
        composeRule.setContent {
            RatingStar(rating = 4.5)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(4)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(0)
    }

    @Test
    fun ratingStar_givenFourPointSixRate_FiveFilledStar() {
        composeRule.setContent {
            RatingStar(rating = 4.6)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(5)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(0)
    }

    @Test
    fun ratingStar_givenMinusRate_FiveEmptyStar() {
        composeRule.setContent {
            RatingStar(rating = -1.5)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }

    @Test
    fun ratingStar_givenInvalidRate_FiveEmptyStar() {
        composeRule.setContent {
            RatingStar(rating = 5.1)
        }

        composeRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }
}