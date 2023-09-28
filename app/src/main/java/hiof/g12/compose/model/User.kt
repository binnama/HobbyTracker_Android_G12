package hiof.g12.compose.model

data class User (
    //@DocumentId val uid : String = "", // Open when FireBase is online
    val userName : String = "",
    val password : String = "",
    val email : String = ""
)