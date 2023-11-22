package hiof.g12.compose.screen.diary.diarydetail

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.AlertDialogComponent
import hiof.g12.compose.model.Diary
import hiof.g12.compose.ui.theme.BackGroundColor
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun DiaryDetailScreen(navController: NavController, diaryId: String?, viewModel: DiaryDetailViewModel = hiltViewModel()) {
    val currentDiaryState by viewModel.currentDiary.collectAsState()


    LaunchedEffect(diaryId) {
        viewModel.fetchDiaryDetails(diaryId ?: "")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("DETAIL DIARY", navController)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp, bottom = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (currentDiaryState == null) {
                    Text(text = "Laster...")
                } else {
                    val formattedStartDate =
                        currentDiaryState?.startDate?.let {
                            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                                it
                            )
                        }
                    val formattedStopDate =
                        currentDiaryState?.stopDate?.let {
                            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                                it
                            )
                        }
                    Text(text = "Activity: " + currentDiaryState?.description, color = Color.White)

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Hobby: " + currentDiaryState?.hobby?.title, color = Color.White)

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Start time: " + formattedStartDate, color = Color.White)

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Stop time: " + formattedStopDate, color = Color.White)

                    Spacer(modifier = Modifier.height(30.dp))
                    
                    if (currentDiaryState?.socialMedia != true) {
                        Text(text = "This activity is not shared to socials. Share it!", color = Color.White)
                        EditSocialMediaStatus(diaryId, viewModel,  "Do you want to share this on socials?", currentDiaryState?.socialMedia!!)
                    } else {
                        Text(text = "This activity has been shared to socials.", color = Color.White)
                        EditSocialMediaStatus(diaryId, viewModel,  "Do you want to remove this from socials?",
                            currentDiaryState?.socialMedia!!
                        )

                    }

                    Button(onClick = { navController.popBackStack()}) {
                        Text(text = "Back")
                    }
                   
                }
            }
        }
    }
}

@Composable
fun EditSocialMediaStatus(diaryId: String?, viewModel: DiaryDetailViewModel = hiltViewModel(), dialogText: String?, currentState: Boolean) {
    var toggleDialog by remember { mutableStateOf(false) }

    Button(onClick = { toggleDialog = true}) {
        Text(text = "Social Media")
    }

    if (toggleDialog) {
        AlertDialogComponent(
            onDismissRequest = { toggleDialog = false },
            onConfirmation = { if (diaryId != null) {
                viewModel.updateSocialStatus(diaryId, currentState )
            }
                toggleDialog = false },
            dialogTitle = "Social Status",
            dialogText = dialogText!!,
            icon = Icons.Filled.Share
        )
    }
}