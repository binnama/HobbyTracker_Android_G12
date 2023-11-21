package hiof.g12.compose.screen.home

import TopBar
import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.HobbyDropDownMenuComponent
import hiof.g12.component.TimerComponent
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.screen.diary.DiaryViewModel
import hiof.g12.compose.screen.hobbies.HobbiesViewModel
import java.util.Timer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, viewModel: DiaryViewModel = hiltViewModel(),
) {

    //DropDown
    //val hobbies by viewModel.hobbies.collectAsState(emptyList())
    //val listHobbies = hobbies.toList()
    var dSelectedText by remember { mutableStateOf(listHobbies[0]) }
    var dSelectedText by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Home", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Innhold her

                if (hobbies.isNotEmpty()) {
                    hobbies.forEach { hobby ->
                        HobbyItem(hobby = hobby)
                    }
                    /*
                    LazyColumn {
                        items(count = hobbies.size) { index ->
                            HobbyItem(hobby = hobbies[index])
                        }
                    }
                     */
                } else {
                    Text(
                        text = "Empty, try adding some new hobbies.",
                        color = Color.Gray
                    )
                }

                HobbyDropDownMenuComponent()

                TimerComponent()

            }
        }
    }
}

@Composable
fun HobbyItem(hobby: Hobby) {

    //parseColor fra https://developermemos.com/posts/using-hex-colors-compose
    val color = Color(android.graphics.Color.parseColor(hobby.color))
    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Color.White), modifier = Modifier.widthIn(min = 300.dp)) {
        Text(text = hobby.title)
    }
}
