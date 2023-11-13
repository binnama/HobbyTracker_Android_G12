package hiof.g12.compose.screen.authentication.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class WelcomeViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun authenticateAnonymously(onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try{
                auth.signInAnonymously().await()
                onSuccess.invoke()
            } catch (e: Exception){
                onFailure.invoke(e)
            }
        }
    }
}