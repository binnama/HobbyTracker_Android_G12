package hiof.g12.compose.navigation

import androidx.compose.material.icons.Icons
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
        label ="Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItems(
        label ="My Hobbies",
        icon = Icons.Default.List,
        route = Screens.HobbiesScreen.name
    ),
    NavItems(
        label ="My Diary",
        icon = Icons.Default.Edit,
        route = Screens.MyDiaryScreen.name
    ),
)