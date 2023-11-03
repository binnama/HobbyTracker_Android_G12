package hiof.g12.compose.viewModels

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hiof.g12.compose.service.AuthService
import hiof.g12.compose.service.module.FirebaseModule
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    val db = Firebase.firestore
    var firebaseModule = FirebaseModule()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val authService = AuthService(firebaseAuth)

    // State for Textfields in RegisterScreen.kt
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var registrationComplete by mutableStateOf(false)


    fun handleRegistrationWithChecks(username: String, email: String, password: String){
        if(username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()){

            //Should this be made into a string resource?
            errorMessage = "All fields are required"
            return
        }
        if(password != confirmPassword){

            //Should this be made into a string resource?
            errorMessage = "Passwords do not match"
            return
        }

        firebaseModule.isUsernameUnique("Users",username){ it ->
            if(it){
              errorMessage = "Username is already taken"
            }
            else {
                addUsernameAndRegisterUser(username, email, password)
            }
        }

        // Resets errorMessage
        errorMessage = null
    }

    fun registerUser(email:String, password: String, onComplete: (Boolean) -> Unit){
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){

                    errorMessage = null
                    onComplete(true)
                } else {
                    errorMessage = task.exception?.message?: "Registration failed."
                    onComplete(false)
                }
            }
    }

    fun addUsernameAndRegisterUser(username: String, email: String, password: String) {
        registerUser(email, password) { isSuccessful ->
            if (isSuccessful) {
                // Bruk viewModelScope for å starte en coroutine
                viewModelScope.launch {
                    // Forsøk å logge inn brukeren ved hjelp av AuthService
                    val loginResult = authService.loginUser(email, password)
                    if (loginResult.isSuccess) {
                        // Hvis login er vellykket, hent UID og opprett dokument i Firestore
                        val uid = loginResult.getOrNull()
                        uid?.let {
                            val userDocument = hashMapOf("id" to uid)
                            db.collection("Users").document(username).set(userDocument)
                                .addOnSuccessListener {
                                    // Håndter vellykket oppretting av dokumentet her.
                                    Log.d("Firestore", "DocumentSnapshot successfully written!")
                                }
                                .addOnFailureListener { e ->
                                    Log.w("Firestore", "Error writing document", e)
                                    errorMessage = e.message
                                }
                        }
                    } else {
                        // Hvis login feiler, sett feilmelding
                        errorMessage = loginResult.exceptionOrNull()?.message ?: "Login failed."
                    }
                }
            } else {
                // Hvis registrering feiler, vil errorMessage allerede være satt
            }
        }
    }
}