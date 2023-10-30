package hiof.g12.compose.navigation.LoginNRegister

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object WelcomeScreen : Screen()
    object LoginScreen : Screen()
    object RegisterScreen : Screen()
    object TermsAndCondScreen : Screen()
    object HomeScreen : Screen()
}

object StartpagesRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.WelcomeScreen)

    fun navigateTo(destnation: Screen) {
        currentScreen.value = destnation
    }
}