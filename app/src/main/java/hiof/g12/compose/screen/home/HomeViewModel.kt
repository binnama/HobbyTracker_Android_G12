package hiof.g12.compose.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import hiof.g12.compose.model.Diary

class HomeViewModel: ViewModel() {

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()
}