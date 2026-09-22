package com.example.zenzy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ZenzyNavigationTest {

    // This rule launches your MainActivity before the test starts
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun funSplashToHomeNavigationTest() {
        // 1. Verify we are starting on the Splash Screen by looking for its unique text
        composeTestRule.onNodeWithText("Zenzy").assertIsDisplayed()
        composeTestRule.onNodeWithText("A living map for what's happening right now.").assertIsDisplayed()

        // 2. Simulate a user tapping the "Get Started" button
        composeTestRule.onNodeWithText("Get Started").performClick()

        // 3. Verify we arrived at the Home Screen by checking for map overlay elements
        composeTestRule.onNodeWithText("Search places, events, friends...").assertIsDisplayed()
        composeTestRule.onNodeWithText("Friends").assertIsDisplayed()
    }
}
