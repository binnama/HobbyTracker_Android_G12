package hiof.g12.compose.screen.home

import TopBar
import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.HobbyDropDownMenuComponent
import hiof.g12.component.TimerComponent
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.screen.hobbies.HobbiesViewModel
import java.util.Timer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, viewModel: HobbiesViewModel = hiltViewModel(),
) {
    var isRunning by remember { mutableStateOf(false) }
    val timePassed: Long = 0
    var elapsedSecs by remember { mutableStateOf(timePassed) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    //DropDown
    val hobbies by viewModel.hobbies.collectAsState(emptyList())
    val listHobbies = hobbies.toList()
    var dSelectedText by remember { mutableStateOf(listHobbies[0]) }
    //var dSelectedText by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Home", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Innhold her

                HobbyDropDownMenuComponent()

                TimerComponent()

            }
        }
    }
}
