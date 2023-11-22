package hiof.g12.compose.screen.hobbies
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
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

    fun addHobby(hobbyTitle: String, hobbyColor: String) {
        viewModelScope.launch {
            val newHobby = Hobby(title = hobbyTitle, color= hobbyColor, userId = accountService.currentUserId)

            storageService.saveHobby(newHobby)
        }
    }
}