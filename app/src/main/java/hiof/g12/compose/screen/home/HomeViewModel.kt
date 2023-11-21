package hiof.g12.compose.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class HomeViewModel: ViewModel() {

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    val hobbyTimes: MutableState<List<HobbyTimer>> = mutableStateOf(emptyList())

    fun saveTrackedTime(userId: String, hobbyTitle: String) {
        db.collection("hobbies")
            .whereEqualTo("userId", userId)
            .whereEqualTo("title", hobbyTitle)
            .get()
            .addOnSuccessListener { documents ->
                if (documents != null && !documents.isEmpty) {
                    val hobbyId = documents.documents[0].id
                    db.collection("hobbies")
                        .document(hobbyId)
                        .collection("times")
                        .get()
                        .addOnSuccessListener { timesDocuments ->
                            val hobbyTimesList = timesDocuments.documents.mapNotNull { document ->
                                document.toObject(HobbyTimer::class.java)
                            }
                            hobbyTimes.value = hobbyTimesList
                        }
                        .addOnFailureListener { e ->
                            //Need error-handling
                        }
                }
            }
            .addOnFailureListener { e ->
                //Need error-handling
            }
    }


    /*
    Tid på nåværende aktivitet var

    Aktiv aktivitet var

    (Aktiv aktivitets kategori var)

    Lage liste elementer av hobbier og kategorier funksjon
     */


}

data class HobbyTimer(
    val timestamp: String,
    val duration: Long
)