package hiof.g12.compose.screen.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.service.AccountService
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {

    val isAnonymous = accountService.currentUser.map { it.isAnonymous }

    fun onSignOutClick(navController : NavController) {
        viewModelScope.launch {
            accountService.signOut()
            navController.navigate(Screens.WelcomeScreen.name)
        }
    }
}