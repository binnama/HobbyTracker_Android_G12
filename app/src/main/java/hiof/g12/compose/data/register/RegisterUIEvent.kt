package hiof.g12.compose.data.register

sealed class RegisterUIEvent {

    data class UsernameChanged (val username: String ) : RegisterUIEvent()
    data class EmailChanged (val email: String ) : RegisterUIEvent()
    data class PasswordChanged (val password: String ) : RegisterUIEvent()

    object RegisterButtonClicked : RegisterUIEvent()
}
