package hiof.g12.compose.model

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Hobby (
    @DocumentId
    val uid : String = "",
    val title: String = "",
    val color: String = "",
    var userId: String = "") {
    constructor() : this("", "" ,"", "")
}