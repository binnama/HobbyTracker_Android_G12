package hiof.g12.compose.service

import hiof.g12.compose.model.User
import kotlinx.coroutines.flow.Flow

// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun signOut()
}