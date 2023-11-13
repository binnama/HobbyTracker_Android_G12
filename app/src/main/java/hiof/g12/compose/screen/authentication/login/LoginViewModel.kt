package hiof.g12.compose.screen.authentication.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.R
import hiof.g12.compose.common.ext.isValidEmail
import hiof.g12.compose.common.ext.isValidPassword
import hiof.g12.compose.service.AccountService
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onLoginClick(loggedIn: () -> Unit) {
        if (!email.isValidEmail()) {
            uiState.value = uiState.value.copy(errorMessage = R.string.email_error)
            return
        }

        if (!password.isValidPassword()) {
            uiState.value = uiState.value.copy(errorMessage = R.string.password_error)
            return
        }

        viewModelScope.launch {
            try {
                accountService.authenticate(email, password) { error ->
                    if (error == null)
                        loggedIn()
                }
            }
            catch(e: Exception) {
                uiState.value = uiState.value.copy(errorMessage = R.string.could_not_log_in)
            }
        }
    }
}