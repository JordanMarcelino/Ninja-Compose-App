package com.example.boruto_compose.ui.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.boruto_compose.R
import org.junit.Rule
import org.junit.Test

class InfoBoxTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun infoBox_givenText_sameText() {
        composeRule.setContent {
            InfoBox(
                smallText = "Power",
                bigText = "92",
                icon = painterResource(id = R.drawable.bolt),
                iconColor = Color.Black,
                textColor = Color.White
            )
        }

        composeRule.onNodeWithContentDescription("InfoBoxSmallText")
            .assertTextEquals("Power")
        composeRule.onNodeWithContentDescription("InfoBoxBigText")
            .assertTextEquals("92")
        composeRule.onNodeWithContentDescription("InfoBoxIcon")
            .assertExists()
            .assertIsDisplayed()
    }
}