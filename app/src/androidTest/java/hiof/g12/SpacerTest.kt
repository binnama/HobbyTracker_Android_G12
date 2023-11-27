package hiof.g12

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidTest
import hiof.g12.component.SpacerComponent
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SpacerTest {

    // Utfører en ganske enkel UI test.
    // Sjekker bare at denne spaceren ikke skal ha noe tekst.


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verify_SpacerComponent_shouldNotHaveText() {

        composeTestRule.setContent {
            SpacerComponent()
        }

        composeTestRule.onNodeWithText("Hi").assertDoesNotExist()
    }

}