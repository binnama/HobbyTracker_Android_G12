package hiof.g12.compose.screen.diary.diarydetail

import ListItemComponent
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
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import hiof.g12.component.ProgressComponent
import hiof.g12.component.SpacerComponent
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.ui.theme.backGroundHex
import hiof.g12.features.convertDateToLocalFormat
import hiof.g12.features.convertToMinutesAndSeconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryDetailScreen(navController: NavController, diaryId: String?, viewModel: DiaryDetailViewModel = hiltViewModel()) {
    val currentDiaryState by viewModel.currentDiary.collectAsState()

    // Setter disse i en variabell slik at jeg kan bruke det senere
    val startDate = currentDiaryState?.startDate
    val stopDate = currentDiaryState?.stopDate

    LaunchedEffect(diaryId) {
        viewModel.fetchDiaryDetails(diaryId ?: "")
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar= {TopBar("Diary Detail", navController)},
        containerColor = BackGroundColor) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (currentDiaryState == null) {
                ProgressComponent()
            } else {
                Button(onClick = { navController.popBackStack()}) {
                    Text(text = "Back")
                }
                SpacerComponent(30)
                ListItemComponent("Activity", currentDiaryState?.description, Icons.Filled.DirectionsRun)
                ListItemComponent("Hobby", currentDiaryState?.hobby?.title, Icons.Filled.Accessibility)

                if (startDate != null && stopDate != null) {
                    val formattedStartDate = convertDateToLocalFormat(startDate)
                    val formattedStopDate = convertDateToLocalFormat(stopDate)
                    val formattedTimeSpent = convertToMinutesAndSeconds(startDate, stopDate)

                    ListItemComponent("Started", formattedStartDate, Icons.Filled.Start)
                    ListItemComponent("Stopped", formattedStopDate, Icons.Filled.StopCircle)
                    ListItemComponent("Time elapsed in minutes", formattedTimeSpent, Icons.Filled.Timelapse)
                } else {
                    ListItemComponent("Time", "Can't calculate time. Have you stopped your activity?", Icons.Filled.Timelapse)
                }

                if (currentDiaryState?.socialMedia != true) {
                    ListItemComponent("Social Media", "This activity has not been shared to the public", Icons.Filled.Share)
                    EditSocialMediaStatus(diaryId, viewModel,  "Do you want to share this on socials?", currentDiaryState?.socialMedia!!)
                } else {
                    ListItemComponent("Social Media", "This activity has been shared to the public", Icons.Filled.Share)
                    EditSocialMediaStatus(diaryId, viewModel,  "Do you want to remove this from socials?",
                        currentDiaryState?.socialMedia!!
                    )

                }
            }
        }
    }
}


@Composable
fun EditSocialMediaStatus(diaryId: String?, viewModel: DiaryDetailViewModel = hiltViewModel(), dialogText: String?, currentState: Boolean) {
    var toggleDialog by remember { mutableStateOf(false) }
    SpacerComponent(30)
    Button(onClick = { toggleDialog = true}) {
        if (currentState == true) {
            Text(text = "Unshare")
        } else {
            Text(text = "Share")
        }
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