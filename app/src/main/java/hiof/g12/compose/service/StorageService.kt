package hiof.g12.compose.service
import hiof.g12.compose.model.Hobby

import kotlinx.coroutines.flow.Flow

// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
interface StorageService {

    // Hobbies Storage
    val hobbies: Flow<List<Hobby>>
    suspend fun getHobby(hobbyId: String): Hobby?

    suspend fun saveHobby(hobby: Hobby): String

    // Time storage
    //suspend fun
}

