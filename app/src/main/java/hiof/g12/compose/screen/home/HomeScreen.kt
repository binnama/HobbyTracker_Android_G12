package hiof.g12.compose.screen.home

import TopBar
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.compose.model.Diary
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.screen.diary.mydiary.DiaryViewModel
import hiof.g12.compose.screen.hobbies.HobbiesViewModel
import hiof.g12.compose.ui.theme.ButtonColorBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, viewModel: DiaryViewModel = hiltViewModel()
) {

    // https://developer.android.com/jetpack/compose/state

    val activeDiaryState = viewModel.activeDiary.collectAsState()

    val activeDiary = activeDiaryState.value

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Home", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Check if activeDiary is null or not
                if (activeDiary == null) {
                    InActiveActivity()
                } else {
                    ActiveActivity(activeDiary!!)
                }

            }
        }
    }
}

@Composable
fun ActiveActivity(activeDiary: Diary, viewModel: DiaryViewModel = hiltViewModel()) {
    val context = LocalContext.current

    Text(
                text = "PÅGÅENDE AKTIVITET",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White

                )
            Text(text = "Aktiv aktivitet: " + activeDiary.description, fontSize = 24.sp,
                color = Color.White)
            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Hobby: " + activeDiary.hobby.title, fontSize = 24.sp,
                color = Color.White)
            Spacer(modifier = Modifier.height(30.dp))

            // Her skal timer...
            Text(text = "00.00.00 ", fontSize = 24.sp,
                color = Color.White)
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { viewModel.stopActivity(activeDiary.uid)
                    Toast.makeText(context, "Avsluttet en aktivitet", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ), modifier = Modifier
                    .widthIn(min = 300.dp)
                    .heightIn(48.dp)
            ) {
                Text(
                    text = "Stopp", fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InActiveActivity(viewModel: DiaryViewModel = hiltViewModel()) {

    var activityText by remember { mutableStateOf("") }

    var selectedHobby by remember { mutableStateOf<Hobby?>(null) }

    val context = LocalContext.current

    Text(text = "Start an activity right now, stop being lazy", color = Color.White)
    Spacer(modifier = Modifier.height(30.dp))

    Text(text = "Give your activity a name", color = Color.White)
    TextField(value = activityText, onValueChange = {activityText = it})
    Spacer(modifier = Modifier.height(30.dp))
    HobbyDropDownMenu(onHobbySelected = { selectedHobby = it })
    Spacer(modifier = Modifier.height(30.dp))

    if (selectedHobby != null) {
        Button(
            onClick = { viewModel.addDiary(activityText, selectedHobby!!)
                Toast.makeText(context, "Startet en aktivitet", Toast.LENGTH_SHORT).show()
            },
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