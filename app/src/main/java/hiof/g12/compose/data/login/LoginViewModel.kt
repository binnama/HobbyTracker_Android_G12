package hiof.g12.compose.data.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hiof.g12.compose.data.register.RegisterViewModel

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.UsernameChange -> {
                loginUIState.value = loginUIState.value.copy(
                    username = event.username
                )
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
    }

    private fun login() {
        TODO("Not yet implemented")
    }

}