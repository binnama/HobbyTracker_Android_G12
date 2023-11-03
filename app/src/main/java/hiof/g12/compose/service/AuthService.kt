package hiof.g12.compose.service

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

enum class AuthenticationStatus {
    Authenticated,
    Unauthenticated
}
class AuthService(private val auth: FirebaseAuth) {

    private val _authenticationStatus = MutableStateFlow(AuthenticationStatus.Unauthenticated)
    val authenticationStatus: StateFlow<AuthenticationStatus> = _authenticationStatus.asStateFlow()

    suspend fun loginUser(email: String, password: String): Result<String> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            val uid = auth.currentUser?.uid
            // Suksess, returnerer UID til den innloggede brukeren
            if (uid != null) {
                Result.success(uid)
            } else {
                // Noe gikk galt, ingen UID funnet
                Result.failure(Exception("No UID found for the logged-in user."))
            }
        } catch (e: Exception) {
            // Innlogging feilet, returner feilen
            Result.failure(e)
        }
    }
    fun observeAuthState() {
        auth.addAuthStateListener { firebaseAuth ->
            _authenticationStatus.value = if (firebaseAuth.currentUser != null) {
                AuthenticationStatus.Authenticated
            } else {
                AuthenticationStatus.Unauthenticated
            }
        }
    }

    fun logoutUser() {
        auth.signOut()
        _authenticationStatus.value = AuthenticationStatus.Unauthenticated
    }
}