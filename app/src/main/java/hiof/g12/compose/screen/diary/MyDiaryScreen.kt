package hiof.g12.compose.screen.diary

import TopBar
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.AlertDialogComponent
import hiof.g12.compose.model.Diary
import hiof.g12.compose.ui.theme.BackGroundColor
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MyDiaryScreen(navController: NavController, viewModel: DiaryViewModel = hiltViewModel()) {

    val myDiaries by viewModel.diaries.collectAsState(emptyList())


    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Diary", navController)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp, bottom = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (myDiaries.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = 600.dp)
                            .padding(16.dp)
                    ) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(count = myDiaries.size) { index ->
                                DiaryItem(navController, diary = myDiaries[index])
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Sad life, no diaries? Add a new one!",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun DiaryItem(navController: NavController, diary: Diary, viewModel: DiaryViewModel = hiltViewModel()) {
    val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(diary.startDate)

    var toggleDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    fun String.toColor() = Color(android.graphics.Color.parseColor(this))
    val color = diary.hobby.color.toColor()
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 240.dp, height = 200.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center, // Align content vertically centered
            horizontalAlignment = Alignment.CenterHorizontally, // Align content horizontally centered
            modifier = Modifier.fillMaxSize() // Take the full available size
        ) {
            Row {
                Text(
                    text = formattedDate,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Button(
                    onClick = { toggleDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
                if (diary.socialMedia == true) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                }
            }

            Text(
                text = diary.description,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Button(
                onClick = { navController.navigate("DiaryDetailScreen/${diary.uid}") },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Vis")
            }
        }


        if (toggleDialog) {
            AlertDialogComponent(
                onDismissRequest = { toggleDialog = false },
                onConfirmation = { viewModel.deleteDiary(diary.uid)
                                 toggleDialog = false
                    Toast.makeText(context, "Diary successfully deleted", Toast.LENGTH_SHORT).show()
                },
                dialogTitle = "Delete diary",
                dialogText = "Do you want to delete this diary?",
                icon = Icons.Filled.Delete
            )
        }
    }
}