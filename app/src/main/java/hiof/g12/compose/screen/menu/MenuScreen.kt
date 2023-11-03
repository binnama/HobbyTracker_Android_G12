package hiof.g12.compose.screen.menu

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
import hiof.g12.component.MenuTopBar
import hiof.g12.compose.navigation.Screens
import hiof.g12.ui.theme.BackGroundColor

@Composable
fun MenuScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            MenuTopBar(navController = navController)
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(Screens.HomeScreen.name) {

                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Min Profil")
                }
                Button(
                    onClick = { navController.navigate(Screens.HomeScreen.name) {

                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Innstillinger")
                }
                Button(
                    onClick = { navController.navigate(Screens.HomeScreen.name) {

                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Om Oss")
                }

                // Mer innhold her
            }
        }
    }
}
