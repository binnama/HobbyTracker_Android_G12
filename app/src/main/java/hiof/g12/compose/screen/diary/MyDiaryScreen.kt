package hiof.g12.compose.screen.diary

import TopBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.screen.hobbies.HobbyItem
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.compose.ui.theme.ButtonColorBlue
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
                                DiaryItem(diary = myDiaries[index])
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Sad life, no diaries? Add a new one!",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun DiaryItem(diary: Diary) {
    val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(diary.startDate)
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 240.dp, height = 200.dp)
    ) {
        Column {
            Row {
                Text(
                    text = formattedDate,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Red
                )) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
                if(diary.socialMedia == true) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                }
            }
        }
        Text(
            text = diary.description,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}