package hiof.g12.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf(

    NavItems(
        label ="Hobbies",
        icon = Icons.Default.List,
        route = Screens.HobbiesScreen.name
    ),
    NavItems(
        label ="Calendar",
        icon = Icons.Default.CalendarMonth,
        route = Screens.CalendarScreen.name
    ),
    NavItems(
        label ="Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItems(
        label ="Social",
        icon = Icons.Default.AccountBox,
        route = Screens.SocialScreen.name
    ),
    NavItems(
        label ="Diary",
        icon = Icons.Default.Edit,
        route = Screens.MyDiaryScreen.name
    )
)