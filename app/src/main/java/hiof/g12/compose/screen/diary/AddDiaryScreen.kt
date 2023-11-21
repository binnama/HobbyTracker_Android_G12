package hiof.g12.compose.screen.hobbies

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hiof.g12.compose.screen.diary.DiaryViewModel

import hiof.g12.compose.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiaryScreen(navController: NavController, viewModel: DiaryViewModel = hiltViewModel()) {
    var diaryDescription by remember { mutableStateOf("") }
    val shareToSocial = remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Add Diary", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(value = diaryDescription, onValueChange = { diaryDescription = it }, label = { Text(text = "Add diary text") })
                Spacer(modifier = Modifier.height(30.dp))

                Row {
                    Checkbox(
                        checked = shareToSocial.value,
                        modifier = Modifier.padding(16.dp),
                        onCheckedChange = { isChecked -> shareToSocial.value = isChecked },
                    )
                    Text("Add to social media", color = Color.White)
                }

                Button(
                    onClick = {
                        viewModel.addDiary(diaryDescription)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Add")
                }
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}
