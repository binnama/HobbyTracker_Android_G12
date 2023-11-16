package hiof.g12.compose.screen.hobbies
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HobbiesViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {

    val hobbies = storageService.hobbies

    fun addHobby(hobbyTitle: String) {
        viewModelScope.launch {
            val newHobby = Hobby(title = hobbyTitle, userId = accountService.currentUserId)

            storageService.saveHobby(newHobby)
        }
    }
}