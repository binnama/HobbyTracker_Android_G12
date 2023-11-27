package hiof.g12.compose.service.impl

import com.google.firebase.auth.FirebaseAuth
import hiof.g12.compose.model.User
import hiof.g12.compose.service.AccountService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


// Denne koden ble hentet av forelesningen under modul: Firebase Authentication

// Dette er en implementasjon av AccountService interface.
class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val currentUserEmail: String
        get() = auth.currentUser?.email.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null


    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid, it.email.orEmpty(), it.isAnonymous) } ?: User())
                }

            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { onResult(it.exception) }.await()
    }
    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        // Av en eller annen grunn har fungerer ikke linkingen av kontoer nå?
        // val credential = EmailAuthProvider.getCredential(email, password)
        // auth.currentUser!!.linkWithCredential(credential).addOnCompleteListener { onResult(it.exception) }.await()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { onResult(it.exception) }.await()
    }

    override suspend fun signOut() {
        auth.signOut()

        auth.signInAnonymously()
    }
}