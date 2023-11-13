package hiof.g12.compose.screen.authentication.login

import androidx.annotation.StringRes

// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    @StringRes val errorMessage: Int = 0
)
