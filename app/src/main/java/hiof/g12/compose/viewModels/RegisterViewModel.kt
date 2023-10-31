package hiof.g12.compose.viewModels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {

    // State for Textfields in RegisterScreen.kt
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)


    fun handleRegistration(){
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
        // Neste steg
        registerUser(email, password)

        // Resets errorMessage
        errorMessage = null
    }

    fun registerUser(email:String, password: String){
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    errorMessage = null
                } else {
                    errorMessage = task.exception?.message?: "Registration failed."
                }
            }
    }
}