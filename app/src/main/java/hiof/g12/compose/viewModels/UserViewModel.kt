package hiof.g12.compose.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserViewModel : ViewModel() {
    private val db = Firebase.firestore

    /*
    suspend fun fetchUserActivities(userId: String): List<ActivityData> {
        val activities = mutableListOf<ActivityData>()

        try {
            // Query the activities collection for the specified user
            val querySnapshot = db.collection("Users").document(userId)
                .collection("Activities")
                .whereGreaterThan("endDate", oneWeekAgoTimestamp) // Filter by the past week
                .get()
                .await()

            for (document in querySnapshot.documents) {
                // Convert Firestore document to ActivityData
                val activity = document.toObject(ActivityData::class.java)
                if (activity != null) {
                    activities.add(activity)
                }
            }
        } catch (e: Exception) {
            // Handle any errors
        }

        return activities
    }
    */
}