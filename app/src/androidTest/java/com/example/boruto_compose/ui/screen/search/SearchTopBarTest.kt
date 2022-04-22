package com.example.boruto_compose.ui.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class SearchTopBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun searchTopBar_givenText_returnTheSameText() {
        val text = mutableStateOf("")
        composeRule.setContent {
            SearchTopBar(
                text = text.value,
                onValueChange = { text.value = it },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }

        composeRule.onNodeWithContentDescription("SearchTextField")
            .performTextInput("Naruto")
        composeRule.onNodeWithContentDescription("SearchTextField")
            .assertTextEquals("Naruto")

        assertThat(text.value).isEqualTo("Naruto")
    }

    @Test
    fun searchTopBar_givenTextAndPressCloseButton_returnEmptyText() {
        val text = mutableStateOf("")
        composeRule.setContent {
            SearchTopBar(
                text = text.value,
                onValueChange = { text.value = it },
                onSearchClicked = {},
                onCloseClicked = {}
            )

        }
        composeRule.onNodeWithContentDescription("SearchTextField")
            .performTextInput("Naruto")
        composeRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeRule.onNodeWithContentDescription("SearchTextField")
            .assertTextContains("")

        assertThat(text.value).isEqualTo("")
    }

    @Test
    fun searchTopBar_givenTextAndPressCloseButtonTwice_searchTopBarDoesntExist() {
        val text = mutableStateOf("")
        val exist = mutableStateOf(true)
        composeRule.setContent {
            if (exist.value) {
                SearchTopBar(
                    text = text.value,
                    onValueChange = { text.value = it },
                    onSearchClicked = {},
                    onCloseClicked = { exist.value = false }
                )
            }
        }

        composeRule.onNodeWithContentDescription("SearchTextField")
            .performTextInput("Naruto")
        composeRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeRule.onNodeWithContentDescription("SearchTextField")
            .assertTextContains("")
        composeRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeRule.onNodeWithContentDescription("SearchTextField")
            .assertDoesNotExist()

        assertThat(exist.value).isFalse()
    }

    @Test
    fun searchTopBar_givenEmptyTextAndPressCloseButton_searchTopBarDoesntExist() {
        val text = mutableStateOf("")
        val exist = mutableStateOf(true)
        composeRule.setContent {
            if (exist.value) {
                SearchTopBar(
                    text = text.value,
                    onValueChange = { text.value = it },
                    onSearchClicked = {},
                    onCloseClicked = { exist.value = false }
                )
            }
        }

        composeRule.onNodeWithContentDescription("SearchTextField")
            .performTextInput("")
        composeRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeRule.onNodeWithContentDescription("SearchTextField")
            .assertDoesNotExist()

        assertThat(exist.value).isFalse()
    }
}