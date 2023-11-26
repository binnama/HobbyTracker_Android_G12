package hiof.g12.compose.service.impl

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import hiof.g12.features.convertToMinutes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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

    // Hente en spesifikk hobby
    override suspend fun getHobby(hobbyId: String): Hobby? =
        firestore.collection(HOBBY_COLLECTION).document(hobbyId).get().await().toObject()


    // Lagrer en hobby til databasen under brukerId
    override suspend fun saveHobby(hobby: Hobby): String {
        val hobbyWithUserId = hobby.copy(userId = auth.currentUserId)
        return firestore.collection(HOBBY_COLLECTION).add(hobbyWithUserId).await().id
    }


    // Funksjon som kalkulerer tid brukt for en spesifikk hobby
    override suspend fun fetchHobbyUsageTime(hobbyId: String): Int {
        var hobbyUsageTime: Int = 0

        val queryData = firestore.collection(DIARY_COLLLECTION)
            .whereEqualTo(HOBBY_ID_FIELD, hobbyId)
            .get()
            .await()

        // Itererer gjennom dokumentene som ble funnet
        for (document in queryData) {

            // Konvertering fra timestamp til toDate
            // https://stackoverflow.com/questions/38016168/how-to-convert-firebase-timestamp-into-date-and-time
            val firebaseStartDate = document.get("startDate") as Timestamp
            val firebaseStopDate = document.get("stopDate") as? Timestamp

            val startDate = firebaseStartDate.toDate()
            val stopDate = firebaseStopDate?.toDate()

            // Utnytter funksjonen jeg har laget for å konvertere dato til minutter.
            val totalMinAndSeconds = convertToMinutes(startDate, stopDate)

            // Kalkulerer og adderer kun dersom stopDato finnes.
            if (stopDate != null) {
                hobbyUsageTime += totalMinAndSeconds
            }
        }

        // Returnerer (Int) tiden brukt for den aktiviteten.
        return hobbyUsageTime
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
                .orderBy(STOP_DATE, Query.Direction.DESCENDING)
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

    override suspend fun getSocialMediaList(): Flow<List<Diary>> {
        return firestore.collection(DIARY_COLLLECTION)
            .whereEqualTo(SOCIAL_MEDIA, true)
            .orderBy(STOP_DATE, Query.Direction.DESCENDING)
            .dataObjects()
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


    // Henter en spesifikk diary
    override suspend fun getDiary(diaryId: String): Diary? =
        firestore.collection(DIARY_COLLLECTION).document(diaryId).get().await().toObject()


    // Lagrer en ny aktvitet.
    override suspend fun saveDiary(diary: Diary): String {
        try {
            val diaryWithUserId = diary.copy(userId = auth.currentUserId)
            return firestore.collection(DIARY_COLLLECTION).add(diaryWithUserId).await().id
        } catch (e: Exception) {
            println("Error ved lagring av Diary")
            throw e
        }
    }

    // Oppdaterer en social media status
    override suspend fun editSocialMediaStatus(diaryId: String, currentState: Boolean) {
        val socialMediaUpdate = hashMapOf(
            SOCIAL_MEDIA to !(currentState)
        ).toMap()
        try {
            firestore.collection(DIARY_COLLLECTION)
                .document(diaryId)
                .update(socialMediaUpdate)
                .await()
        } catch (e: Exception) {
            println("Error ved oppdatering av social media status")
            throw e
        }
    }

    // Sletter en aktivitet
    override suspend fun deleteDiary(diaryId: String) {
        try {
            firestore.collection(DIARY_COLLLECTION)
                .document(diaryId)
                .delete()
                .await()
        } catch (e: Exception) {
            println("Error ved sletting av diary")
            throw e
        }
    }

    companion object {
        private const val HOBBY_COLLECTION = "hobbies"
        private const val DIARY_COLLLECTION = "diaries"
        private const val USER_ID_FIELD = "userId"
        private const val HOBBY_ID_FIELD = "hobbyId"
        private const val STOP_DATE = "stopDate"
        private const val SOCIAL_MEDIA = "socialMedia"
    }
}