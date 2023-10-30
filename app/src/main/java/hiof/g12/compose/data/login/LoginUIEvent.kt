package hiof.g12.compose.data.login

sealed class LoginUIEvent {
    data class UsernameChange(val username:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
}