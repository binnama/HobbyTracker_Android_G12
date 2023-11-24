package hiof.g12.compose.service
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Objects

// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
interface StorageService {

    // Hobbies Storage
    val hobbies: Flow<List<Hobby>>
    suspend fun getHobby(hobbyId: String): Hobby?

    suspend fun saveHobby(hobby: Hobby): String

    // Diary Storage
    val diaries: Flow<List<Diary>>

    suspend fun getActiveActivity(userId: String): Diary?

    suspend fun stopActiveActivity(diaryId: String)

    suspend fun getDiary(diaryId: String): Diary?

    suspend fun saveDiary(diary: Diary): String

    suspend fun editSocialMediaStatus(diaryId: String, currentState: Boolean)
    suspend fun deleteDiary(diaryId: String)

    suspend fun getSocialMediaList(): Flow<List<Diary>>
}
