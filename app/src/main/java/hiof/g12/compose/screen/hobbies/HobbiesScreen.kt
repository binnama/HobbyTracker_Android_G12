package hiof.g12.compose.screen.hobbies

import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.service.StorageService
import hiof.g12.compose.ui.theme.BackGroundColor


@Composable
fun HobbiesScreen(navController: NavController, viewModel: HobbiesViewModel = hiltViewModel()) {

    val hobbies by viewModel.hobbies.collectAsState(emptyList())

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Hobbies", navController)

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(Screens.AddHobbyScreen.name) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black), modifier = Modifier.widthIn(min = 300.dp)
                ) {
                    Text(text = "Add a new hobby")
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
                if (hobbies.isNotEmpty()) {
                    LazyColumn {
                        items(count = hobbies.size) { index ->
                            HobbyItem(hobby = hobbies[index])
                        }
                    }
                } else {
                    Text(
                        text = "Empty, try adding some new hobbies.",
                        color = Color.Gray
                    )
                }
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
