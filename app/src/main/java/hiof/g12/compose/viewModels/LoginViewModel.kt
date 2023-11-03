package hiof.g12.compose.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import hiof.g12.compose.service.AuthService
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(private val authService: AuthService) : ViewModel() {

    init {
        authService.observeAuthState()
    }

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var errorMessage = mutableStateOf<String?>(null)
    var loginSuccess = mutableStateOf(false)

    fun loginUser() {
        viewModelScope.launch {
            val result = authService.loginUser(email.value, password.value)
            if (result.isSuccess) {
                // Handle successful login
                loginSuccess.value = true
                errorMessage.value = null
            } else {
                // Handle login error
                errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
}