package hiof.g12

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import hiof.g12.compose.screen.authentication.welcome.WelcomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @Before
    fun setUpNavHost() {
        composeTestRule.setContent {

            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            WelcomeScreen(navController = navController)

        }
    }

    @Test
    fun verify_StartDestinationIsWelcomeScreen() {
        composeTestRule
            .onNodeWithText("Welcome")
            .assertIsDisplayed()
    }
}