package hiof.g12.compose.screen.diary

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.ui.theme.ButtonColorBlue

@Composable
fun MyDiaryScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Diary", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Button(
                    onClick = { navController.navigate(Screens.AddHobbyScreen.name) },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonColorBlue, contentColor = Color.White), modifier = Modifier.widthIn(min = 300.dp)
                ) {
                    Text(text = "Add journey")
                }
            }
        }
    }
}
