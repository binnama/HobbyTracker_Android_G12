package hiof.g12.compose.screen.hobbies
import androidx.lifecycle.ViewModel

import com.google.firebase.auth.FirebaseAuth


class HobbiesViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun addHobby(hobbyTitle: String) {

    }
}