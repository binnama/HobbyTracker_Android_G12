package hiof.g12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hiof.g12.compose.navigation.AppNavigation
import hiof.g12.ui.theme.BackGroundColor
import hiof.g12.ui.theme.HobbyTrackerTheme

//val db = Firebase.firestore
//val bc_color = Color(0xFF343436)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HobbyTrackerTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackGroundColor
                ) {
                    AppNavigation()
                    // HobbyTrackerApp()
                }
            }
        }
    }
}


@Preview
    //(showBackground = true)
@Composable
fun HobbyTrackerPreview() {
    HobbyTrackerTheme {
        AppNavigation()
       //HobbyTrackerApp()
    }
}