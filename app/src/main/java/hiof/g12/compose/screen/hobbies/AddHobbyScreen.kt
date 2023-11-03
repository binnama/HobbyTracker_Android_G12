package hiof.g12.compose.screen.hobbies

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

import hiof.g12.ui.theme.BackGroundColor

val Red = Color(0xFFFFC8C8)
val Blue = Color(0xFF9BC2CF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHobbyScreen(navController: NavController) {

    var hobbyText by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopBar("Add Hobby", navController)

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(value = hobbyText, onValueChange = {hobbyText = it}, label = { Text(text = "Add hobby title")})
            Spacer(modifier = Modifier.height(70.dp))
            Button(
                onClick = {
                    // Funksjon for å lagre til database
                    navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                    contentColor = Color.Black
                ),
                modifier = Modifier.widthIn(min = 200.dp)
            ) {
                Text(text = "Add")
            }
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red,
                    contentColor = Color.Black
                ),
                modifier = Modifier.widthIn(min = 200.dp)
            ) {
                Text(text = "Cancel")
            }

            // Her vil de andre knappene mappes ut for å vise til deres hobbier
            }
        }
    }
}