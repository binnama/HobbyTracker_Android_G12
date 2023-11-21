package hiof.g12.compose.model

import com.google.firebase.firestore.DocumentId

data class Hobby (
    @DocumentId
    val uid : String = "",
    val title: String = "",
    val color: String = "",
    var userId: String = "",
    )

fun Hobby.toDisplayString(): String {
    return title
}