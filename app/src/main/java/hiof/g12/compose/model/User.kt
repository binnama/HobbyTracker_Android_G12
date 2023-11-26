package hiof.g12.compose.model

data class User (
    val id: String = "",
    val email: String = "",
    val isAnonymous: Boolean = true
) {
    constructor() : this("", "", true)
}