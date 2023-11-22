package hiof.g12.compose.screen.user

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import hiof.g12.compose.ui.theme.BackGroundColor
import TopBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.firestore.FirebaseFirestore
import hiof.g12.R
import hiof.g12.component.UserNameDisplayComponent
import hiof.g12.component.UserPicture
import hiof.g12.compose.model.Diary
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.screen.diary.DiaryViewModel
import hiof.g12.compose.screen.hobbies.HobbiesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserScreen (navController: NavController, viewModel: DiaryViewModel = hiltViewModel()) {

    val db = FirebaseFirestore.getInstance()
    //val hobbies by viewModel.hobbies.collectAsState(emptyList())
    val hobbies by viewModel.diaryEntries.collectAsState(emptyList())
    

    val userPicPlaceholder = painterResource(id = R.drawable.portrait_placeholder)

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = BackGroundColor
    ) {
        TopBar("UserName", navController)

        UserNameDisplayComponent()
        Spacer(modifier = Modifier.height(50.dp))
        
        UserPicture(painter = userPicPlaceholder,
            contentDescription = "User Picture",
            modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "All activities:")

        if (hobbies.isNotEmpty()) {
            LazyColumn {
                items(count = hobbies.size) { index ->
                    HobbyItem(diary = hobbies[index])
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HobbyItem(diary: Diary, viewModel: DiaryViewModel = hiltViewModel()) {
    
    //val eachActivity by viewModel.

    val start = diary.startDate
    val stop = diary.stopDate

    val spentTime = viewModel.calculateActivityTime(start, stop)
    
    Row {
        Text(text = diary.hobby.title)
        Text(text = spentTime)
    }
}
