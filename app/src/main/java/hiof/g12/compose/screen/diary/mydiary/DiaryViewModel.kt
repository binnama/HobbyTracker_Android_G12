package hiof.g12.compose.screen.diary.mydiary

import android.nfc.Tag
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel  @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {

    // Alle diaries data
    val diaries = storageService.diaries

    // Alle sosiale medier posts
    private val _socialMedia = MutableStateFlow<List<Diary>>(emptyList())
    val socialMedia: StateFlow<List<Diary>> = _socialMedia

    // Aktiv aktivitet hvis den finnes
    private val _activeDiary = MutableStateFlow<Diary?>(null)
    val activeDiary: StateFlow<Diary?> = _activeDiary

    init {
        _socialMedia.value = emptyList()
        fetchActiveDiary()
        fetchSocialPosts()
    }

    private fun fetchActiveDiary() {
        viewModelScope.launch {
            val userId = accountService.currentUserId
            val activeDiary = storageService.getActiveActivity(userId)
            _activeDiary.value = activeDiary
        }
    }

    fun fetchSocialPosts() {
        viewModelScope.launch {
            storageService.getSocialMediaList().collect { socialPosts ->
                _socialMedia.value = socialPosts
            }
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

    val diaryEntries = storageService.diaries
}