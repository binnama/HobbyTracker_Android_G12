package hiof.g12.compose.screen.authentication.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.service.AccountService
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {

    fun authenticateAnonymously(onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try{
                accountService.createAnonymousAccount()
                onSuccess.invoke()
            } catch (e: Exception){
                onFailure.invoke(e)
            }
        }
    }
}