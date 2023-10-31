package hiof.g12.compose.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    fun authenticateWithEmail(onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try{
                auth.signInWithEmailAndPassword(email.value, password.value).await()
                onSuccess.invoke()
            } catch (e: Exception){
                onFailure.invoke(e)
            }
        }
    }
}