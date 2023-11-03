package hiof.g12.compose.service.module

import android.text.LoginFilter.UsernameFilterGMail
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseModule {
    /*
    Uferdig liste hobby var

    Uferdig liste kategori

    Hente ut Liste som holder på alle hobbier fun

    (Hente ut Liste som holder på alle kategorier fun)

    Sende inn start tid fun

    Sende inn stop tid fun
     */

    val db = Firebase.firestore

    fun isUsernameUnique (collectionPath:String,documentName:String, onSuccess: (exists: Boolean) -> Unit) {
        db.collection(collectionPath)
            .document(documentName)
            .get()
            .addOnSuccessListener {onSuccess(it.exists()) }
    }


}