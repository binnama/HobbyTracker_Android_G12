package hiof.g12.compose.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import java.util.Date

data class Diary (
    @DocumentId
    val uid : String = "",
    val description: String = "",
    val startDate: Date = Date(),
    val hobbyId: String?,
    val hobby: Hobby,
    val stopDate: Date? = null,
    val socialMedia: Boolean? = null,
    var userId: String = ""
) {
    constructor() : this("", "", Date(), "", Hobby(), null, null, "")
}