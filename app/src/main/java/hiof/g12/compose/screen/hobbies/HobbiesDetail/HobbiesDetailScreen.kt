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


@Composable
fun HobbiesDetailScreen(navController: NavController, hobbyId: String?, viewModel: HobbiesViewModel = hiltViewModel()) {
    val currentHobbyState by viewModel.currentHobby.collectAsState()
    val hobbyTimeUsage by viewModel.currentHobbbyTime.collectAsState()


    LaunchedEffect(hobbyId) {
        viewModel.fetchHobbyDetails(hobbyId ?: "")
    }
    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar(title = "Hobby: ${currentHobbyState?.title}", navController = navController)
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, bottom = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                if (currentHobbyState == null) {
                    ProgressComponent()
                } else {
                    Button(onClick = { navController.popBackStack()}) {
                        Text(text = "Back")
                    }
                    SpacerComponent(30)
                    ListItemComponent("Hobby", currentHobbyState?.title, Icons.Filled.Accessibility)
                    ListItemComponent("Total time used on this hobby", hobbyTimeUsage.toString() + " minutes", Icons.Filled.Timelapse)
                }
            }
        }
    }
}