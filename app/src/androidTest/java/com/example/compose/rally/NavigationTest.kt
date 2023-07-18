package com.example.compose.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            RallyNavHost(navController = navController)
        }
    }

    @Test
    fun rallyNavHost_test_OverviewScreen_isDisplayed_as_startDestination() {
        composeTestRule.onNodeWithContentDescription(
            "Overview Screen"
        ).assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_test_AccountsScreen_isDisplayed_as_startDestination() {
        composeTestRule.onNodeWithContentDescription(
            "All Accounts"
        ).performClick()

        composeTestRule.onNodeWithContentDescription(
            "Accounts Screen"
        ).assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_test_BillsScreen_isDisplayed() {
        composeTestRule.onNodeWithContentDescription(
            "All Bills"
        ).performScrollTo()
            .performClick()

        composeTestRule.onNodeWithContentDescription(
            "Bills Screen"
        ).assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_test_BillsScreen_isDisplayed_1() {
        composeTestRule.onNodeWithContentDescription(
            "All Bills"
        ).performScrollTo()
            .performClick()

       val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "bills")
    }


}