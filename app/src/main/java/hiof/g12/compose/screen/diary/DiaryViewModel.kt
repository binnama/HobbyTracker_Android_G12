package hiof.g12.compose.screen.diary

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.type.DateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
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
    val activeActivity = mutableStateOf(Diary())

    fun findActivity() {
        viewModelScope.launch {
            storageService.getActiveActivity(userId = accountService.currentUserId)?.let {
                activeActivity.value = it
            }
        }
    }


    fun addDiary(diaryDescription: String, hobby: Hobby) {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance().time
            val newDiary = Diary(description = diaryDescription, startDate = currentDate, hobby= hobby, userId = accountService.currentUserId)
            storageService.saveDiary(newDiary)
        }
    }
}