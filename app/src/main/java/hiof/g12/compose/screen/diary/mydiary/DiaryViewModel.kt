package hiof.g12.compose.screen.diary.mydiary

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.type.DateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel  @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {

    val diaries = storageService.diaries
    val socialMedia = storageService.socialMedia

    private val _activeDiary = MutableStateFlow<Diary?>(null)
    val activeDiary: StateFlow<Diary?> = _activeDiary

    init {
        // Fetch active diary when the ViewModel is initialized
        fetchActiveDiary()
    }

    // Function to fetch the active diary
    init {
        // Fetch active diary when the ViewModel is initialized
        fetchActiveDiary()
    }

    private fun fetchActiveDiary() {
        viewModelScope.launch {
            val userId = accountService.currentUserId
            val activeDiary = storageService.getActiveActivity(userId)
            _activeDiary.value = activeDiary
        }
    }

    fun stopActivity(diaryId: String) {
        viewModelScope.launch {
            storageService.stopActiveActivity(diaryId)
            fetchActiveDiary()
        }
    }

    fun deleteDiary(diaryId: String) {
        viewModelScope.launch {
            storageService.deleteDiary(diaryId)
        }
    }

    fun addDiary(diaryDescription: String, hobby: Hobby) {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance().time
            val newDiary = Diary(description = diaryDescription, startDate = currentDate, socialMedia= false, hobby= hobby, userId = accountService.currentUserId)
            storageService.saveDiary(newDiary)

            fetchActiveDiary()

        }
    }
}