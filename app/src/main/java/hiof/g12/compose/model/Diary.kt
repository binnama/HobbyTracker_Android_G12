package hiof.g12.compose.model

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Diary (
    @DocumentId
    val uid : String = "",
    val description: String = "",
    val date: Date = Date(),
    var userId: String = "")