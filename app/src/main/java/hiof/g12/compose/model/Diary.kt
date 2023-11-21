package hiof.g12.compose.model

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Diary (
    @DocumentId
    val uid : String = "",
    val description: String = "",
    val startDate: Date = Date(),
    val hobby: Hobby,
    val stopDate: Date? = null,
    val socialMedia: Boolean? = null,
    var userId: String = ""
) {
    constructor() : this("", "", Date(), Hobby(), null, null, "")
}