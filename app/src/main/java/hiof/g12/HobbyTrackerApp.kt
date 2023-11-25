package hiof.g12


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hiof.g12.compose.navigation.AppNavigation
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.ui.theme.HobbyTrackerTheme

@SuppressLint("NewApi")
@Composable
fun HobbyTrackerApp() {
            HobbyTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackGroundColor
                ) {
                    AppNavigation()
                }
            }
        }