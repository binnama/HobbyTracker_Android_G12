package hiof.g12.compose.screen.authentication.register


import androidx.annotation.StringRes

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    @StringRes val errorMessage: Int = 0
)
