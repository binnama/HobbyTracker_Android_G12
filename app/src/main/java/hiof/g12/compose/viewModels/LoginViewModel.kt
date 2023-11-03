package hiof.g12.compose.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import hiof.g12.compose.service.AuthService
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val authService = AuthService(firebaseAuth)

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    init {
        authService.observeAuthState()
    }

    fun loginUser(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val loginResult = authService.loginUser(email.value, password.value)
            if (loginResult.isSuccess) {
                onComplete(true) }
             else {
                errorMessage = "Kunne ikke logge inn"
                onComplete(false)
            }
        }
    }
}
