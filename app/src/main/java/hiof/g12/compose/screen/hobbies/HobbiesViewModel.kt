package hiof.g12.compose.screen.hobbies
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HobbiesViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {

    private val _currentHobby = MutableStateFlow<Hobby?>(null)
    val currentHobby: StateFlow<Hobby?> = _currentHobby

    private val _currentHobbyTime = MutableStateFlow<Int?>(0)
    val currentHobbbyTime: StateFlow<Int?> = _currentHobbyTime

    val hobbies = storageService.hobbies

    fun fetchHobbyDetails(hobbyId: String) {
        viewModelScope.launch {
            try {
                val hobby = storageService.getHobby(hobbyId)
                _currentHobby.value = hobby
                fetchHobbyUsageTime(hobbyId)
            } catch (e: Exception) {
                println("Error ved henting av spesifikk hobby")
                throw e
            }
        }
    }

    fun fetchHobbyUsageTime(hobbyId: String) {
        viewModelScope.launch {
            try {
                val hobbyTime = storageService.fetchHobbyUsageTime(hobbyId)
                _currentHobbyTime.value = hobbyTime
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun addHobby(hobbyTitle: String, hobbyColor: String) {
        viewModelScope.launch {
            val newHobby = Hobby(title = hobbyTitle, color= hobbyColor, userId = accountService.currentUserId)

            storageService.saveHobby(newHobby)
        }
    }
}