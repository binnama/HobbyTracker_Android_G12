package hiof.g12.compose.model

import com.google.firebase.firestore.DocumentId

data class Hobby (
    @DocumentId
    val uid : String = "",
    val title: String = "",
    val color: String = "",
    //val timer : HobbyTimer,
    var userId: String = "",
    )


data class HobbyTimer (
    @DocumentId
    val uid : String = "",
    val trackerTimer : String = ""
)
