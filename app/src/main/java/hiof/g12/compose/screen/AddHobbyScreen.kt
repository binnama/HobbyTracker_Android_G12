package hiof.g12.compose.screen

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hiof.g12.compose.navigation.Screens
val Red = Color(0xFFFFC8C8)
val Blue = Color(0xFF9BC2CF)

@Composable
fun AddHobbyScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopBar("Add Hobby")

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
           Text(text = "Her legges ny hobby")
            Button(
                onClick = { navController.popBackStack()},
                colors = ButtonDefaults.buttonColors(containerColor = Blue, contentColor = Color.Black)
            ) {
                Text(text = "Add")
            }
            Button(
                onClick = { navController.popBackStack()},
                colors = ButtonDefaults.buttonColors(containerColor = Red, contentColor = Color.Black)
            ) {
                Text(text = "Cancel")
            }

            // Her vil de andre knappene mappes ut for å vise til deres hobbier
        }
    }
}