package hiof.g12.compose.service.impl

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// Mye av koden ble hentet av forelesningen under modul: Firebase Authentication

class StorageServiceImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
) : StorageService {

    // Henter alle brukernes hobbyer
    @OptIn(ExperimentalCoroutinesApi::class)
    override val hobbies: Flow<List<Hobby>>
        get() = auth.currentUser.flatMapLatest { user ->
            firestore.collection(HOBBY_COLLECTION)
                .where(
                    Filter.or(
                        Filter.equalTo(USER_ID_FIELD, user.id),
                        Filter.equalTo(USER_ID_FIELD, "")
                    )
                )
                .dataObjects()
        }

    // Hente en spesifikk hobby, denne er ikke brukt noen steder foreløpig
    override suspend fun getHobby(hobbyId: String): Hobby? =
        firestore.collection(HOBBY_COLLECTION).document(hobbyId).get().await().toObject()


    // Lagrer en hobby til databasen under brukerId
    override suspend fun saveHobby(hobby: Hobby): String {
        val movieWithUserId = hobby.copy(userId = auth.currentUserId)
        return firestore.collection(HOBBY_COLLECTION).add(movieWithUserId).await().id
    }

    // Henter alle brukernes diaries
    @OptIn(ExperimentalCoroutinesApi::class)
    override val diaries: Flow<List<Diary>>
        get() = auth.currentUser.flatMapLatest { user ->
            firestore.collection(DIARY_COLLLECTION)
                .where(
                    Filter.or(
                        Filter.equalTo(USER_ID_FIELD, user.id),
                        Filter.equalTo(USER_ID_FIELD, "")
                    )
                )
                .dataObjects()
        }


    // Henter pågående aktivitet, hvis den eksisterer, så returneres objektet, hvis ikke, returneres null.
    override suspend fun getActiveActivity(userId: String): Diary? {
        return firestore.collection(DIARY_COLLLECTION)
            .whereEqualTo(USER_ID_FIELD, userId)
            .whereEqualTo(STOP_DATE, null)
            .get()
            .await()
            .toObjects(Diary::class.java)
            .firstOrNull()
    }


    // Stopper pågående aktivitet. Endrer stop date feltet til current tid.
    override suspend fun stopActiveActivity(diaryId: String) {
        val stopDateUpdate = hashMapOf(
            STOP_DATE to FieldValue.serverTimestamp()
        ).toMap()

        firestore.collection(DIARY_COLLLECTION)
            .document(diaryId)
            .update(stopDateUpdate)
            .await()
    }


    // Henter en spesifikk diary, men brukes ikke foreløpig noen steder.
    override suspend fun getDiary(diaryId: String): Diary? =
        firestore.collection(DIARY_COLLLECTION).document(diaryId).get().await().toObject()


    // Lagrer en ny aktvitet.
    override suspend fun saveDiary(diary: Diary): String {
        val diaryWithUserId = diary.copy(userId = auth.currentUserId)
        return firestore.collection(DIARY_COLLLECTION).add(diaryWithUserId).await().id
    }

    companion object {
        private const val HOBBY_COLLECTION = "hobbies"
        private const val DIARY_COLLLECTION = "diaries"
        private const val USER_ID_FIELD = "userId"
        private const val STOP_DATE = "stopDate"
    }
}