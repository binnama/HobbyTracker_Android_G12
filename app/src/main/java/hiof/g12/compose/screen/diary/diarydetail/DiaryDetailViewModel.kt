package hiof.g12.compose.screen.diary.diarydetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hiof.g12.compose.model.Diary
import hiof.g12.compose.service.StorageService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {
    private val _currentDiary = MutableStateFlow<Diary?>(null)
    val currentDiary: StateFlow<Diary?> = _currentDiary

    fun updateSocialStatus(diaryId: String, currentState: Boolean) {
        viewModelScope.launch {
            storageService.editSocialMediaStatus(diaryId, currentState)
            fetchDiaryDetails(diaryId)
        }
    }

    fun fetchDiaryDetails(diaryId: String) {
        viewModelScope.launch {
            try {
                val diary = storageService.getDiary(diaryId)
                _currentDiary.value = diary
            } catch (e: Exception) {
                println("Error ved henting av spesifikk diary")
                throw e
            }
        }
    }
}