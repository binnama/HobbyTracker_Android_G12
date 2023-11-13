package hiof.g12.compose.screen.authentication.register


import androidx.annotation.StringRes

// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    @StringRes val errorMessage: Int = 0
)
