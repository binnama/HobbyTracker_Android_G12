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

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateActivityTime(startTime: Date, stopTime: Date?): String {
        //val formatter = DateTimeFormatter.ISO_DATE_TIME
        val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.getDefault())

        val duration = stopTime?.let {
            it.time - startTime.time
        } ?: 0L
        /*
            val startTime = Instant.from(formatter.parse(startTime.toCustomFormat()))
            val stopTime = stopTime?.let { Instant.from(formatter.parse(it.toCustomFormat())) }

            val duration = if (stopTime != null) {
                Duration.between(startTime, stopTime)
            } else {
                Duration.ZERO
            }


            //val duration = Duration.between(startTime, stopTime)

            val totalMinutes = duration.toMinutes()
            val hours = totalMinutes / 60
            val minutes = totalMinutes % 60
            val seconds = duration.seconds % 60
         */

        val totalMinutes = duration / (1000 * 60)
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        val seconds = duration / 1000 % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    fun Date.toCustomFormat(): String {
        val customDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.getDefault())
        return customDateFormat.format(this)
    }
}