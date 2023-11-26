package hiof.g12.compose.screen.hobbies.HobbiesDetail

import ListItemComponent
import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.DirectionsRun
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.ProgressComponent
import hiof.g12.component.SpacerComponent
import hiof.g12.compose.screen.diary.mydiary.DiaryViewModel
import hiof.g12.compose.screen.hobbies.HobbiesViewModel
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.ui.theme.backGroundHex


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbiesDetailScreen(navController: NavController, hobbyId: String?, viewModel: HobbiesViewModel = hiltViewModel()) {
    val currentHobbyState by viewModel.currentHobby.collectAsState()
    val hobbyTimeUsage by viewModel.currentHobbbyTime.collectAsState()

    LaunchedEffect(hobbyId) {
        viewModel.fetchHobbyDetails(hobbyId ?: "")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar= {TopBar("Hobby: ${currentHobbyState?.title}", navController)},
        containerColor = BackGroundColor) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                if (currentHobbyState == null) {
                    ProgressComponent()
                } else {
                    ListItemComponent("Hobby", currentHobbyState?.title, Icons.Filled.Accessibility)
                    ListItemComponent("Total time used on this hobby", hobbyTimeUsage.toString() + " minutes", Icons.Filled.Timelapse)
                    SpacerComponent()
                    Button(onClick = { navController.popBackStack()}) {
                        Text(text = "Back")
                    }
                }
        }
    }
}