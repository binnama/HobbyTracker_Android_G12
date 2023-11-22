package hiof.g12.compose.screen.home

import TopBar
import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.HobbyDropDownMenuComponent
import hiof.g12.component.TimerComponent
import hiof.g12.compose.model.Diary
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.screen.diary.DiaryViewModel
import hiof.g12.compose.screen.hobbies.HobbiesViewModel
import hiof.g12.compose.ui.theme.ButtonColorBlue
import kotlinx.coroutines.flow.emptyFlow
import java.util.Objects
import java.util.Timer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, viewModel: DiaryViewModel = hiltViewModel()
) {

    val activeActivity by viewModel.activeActivity

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Home", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if(activeActivity == null) {
                    InActiveActivity()
                } else if(activeActivity == Diary()) {
                    ActiveActivity()
                }

            }
        }
    }
}

@Composable
fun ActiveActivity() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InActiveActivity(viewModel: DiaryViewModel = hiltViewModel()) {

    var activityText by remember { mutableStateOf("") }

    var selectedHobby by remember { mutableStateOf<Hobby?>(null) }


    TextField(value = activityText, onValueChange = {activityText = it})
    Spacer(modifier = Modifier.height(30.dp))
    HobbyDropDownMenu(onHobbySelected = { selectedHobby = it })
    Spacer(modifier = Modifier.height(30.dp))

    if (selectedHobby != null) {

        Button(
            onClick = { viewModel.addDiary(activityText, selectedHobby!!)},
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColorBlue,
                contentColor = Color.White
            ), modifier = Modifier
                .widthIn(min = 300.dp)
                .heightIn(48.dp)
        ) {
            Text(
                text = "Start", fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbyDropDownMenu(onHobbySelected: (Hobby) -> Unit, viewModel: HobbiesViewModel = hiltViewModel()) {
    val hobbies by viewModel.hobbies.collectAsState(emptyList())
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedOptionText,
            onValueChange = { selectedOptionText = it },
            label = { Text("Choose a hobby") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        val filteringOptions = hobbies.filter {
            it.title.contains(selectedOptionText, ignoreCase = true)
        }

        if (filteringOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.title) },
                        onClick = {
                            selectedOptionText = selectionOption.title
                            expanded = false
                            onHobbySelected(selectionOption)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}