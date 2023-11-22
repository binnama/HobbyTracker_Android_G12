package hiof.g12.compose.screen.diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel  @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {

    val diaries = storageService.diaries

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

    fun addDiary(diaryDescription: String, hobby: Hobby) {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance().time
            val newDiary = Diary(description = diaryDescription, startDate = currentDate, hobby= hobby, userId = accountService.currentUserId)
            storageService.saveDiary(newDiary)

            fetchActiveDiary()

        }
    }

    val diaryEntries = storageService.diaries

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateActivityTime(startTime: Date, stopTime: Date?): String {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val startTime = Instant.from(formatter.parse(startTime.toString()))
            val stopTime = stopTime?.let { Instant.from(formatter.parse(stopTime.toString())) }

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

            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}