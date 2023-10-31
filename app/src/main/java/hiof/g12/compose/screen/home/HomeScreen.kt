package hiof.g12.compose.screen.home

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import hiof.g12.ui.theme.BackGroundColor

@Composable
fun HomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Home")

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                // Innhold her
            }
        }
    }
}