package hiof.g12.compose.screen.social

import ListItemComponent
import TopBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.SpacerComponent
import hiof.g12.compose.model.Diary
import hiof.g12.compose.screen.diary.mydiary.DiaryViewModel
import hiof.g12.compose.ui.theme.BackGroundColor
import hiof.g12.features.convertDateToLocalFormat

@Composable
fun SocialScreen(navController: NavController, viewModel: DiaryViewModel = hiltViewModel()) {
    val socialMediaList by viewModel.socialMedia.collectAsState(emptyList())


    LaunchedEffect(Unit) {
        viewModel.fetchSocialPosts()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = BackGroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBar("Social", navController)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp, bottom = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                if (socialMediaList.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = 600.dp)
                            .padding(16.dp)
                    ) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(1),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(count = socialMediaList.size) { index ->
                                SocialItem(diary = socialMediaList[index])
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Nobody has shared anything yet.",
                        color = Color.White
                    )

                }
            }
        }
    }
}


@Composable
fun SocialItem(diary: Diary) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        SocialListItemComponent("User", diary.description)
        Row( modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Icon(imageVector = Icons.Filled.AccessTime, contentDescription = "Like button")
            Text(text = " Finished activity at " + diary.stopDate?.let { convertDateToLocalFormat(it) })
        }
        Text(text = " Hobby: " + diary.hobby.title)
        SpacerComponent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialListItemComponent(user: String, description: String) {
    Column {
        ListItem(
            headlineText = {
                Text(
                    text = user + " has shared an activity",
                    fontSize = 20.sp
                )
            },
            supportingText = {
                Text(
                    text = "Activity: " + description,
                    fontSize = 15.sp
                )
            },
            leadingContent = {
                Icon(
                    imageVector= Icons.Filled.PersonPin,
                    contentDescription = "Icon"
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.White,
                headlineColor = Color.Black,
                supportingColor = Color.Black,
                leadingIconColor = Color.Black
            )
        )
        Divider()
    }
}