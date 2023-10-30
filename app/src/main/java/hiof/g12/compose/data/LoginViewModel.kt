package hiof.g12.compose.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent (event: UIEvent) {
        when (event) {
            is UIEvent.UsernameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    username = event.username
                )
                printState()
            }
            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
        }
    }

    private fun printState() {
        Log.d(TAG, "Inide_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }
}