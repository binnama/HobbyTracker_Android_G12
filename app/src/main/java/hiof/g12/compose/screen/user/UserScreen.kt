package hiof.g12.compose.screen.user

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import hiof.g12.ui.theme.BackGroundColor
import TopBar

@Composable
fun UserScreen (navController: NavController) {

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = BackGroundColor
    ) {
        TopBar("UserName")

    }

}