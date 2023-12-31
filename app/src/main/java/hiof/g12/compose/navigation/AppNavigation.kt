package hiof.g12.compose.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hiof.g12.compose.screen.hobbies.AddHobbyScreen
import hiof.g12.compose.screen.calendar.CalendarScreen
import hiof.g12.compose.screen.hobbies.HobbiesScreen
import hiof.g12.compose.screen.home.HomeScreen
import hiof.g12.compose.screen.authentication.login.LoginScreen
import hiof.g12.compose.screen.diary.mydiary.MyDiaryScreen
import hiof.g12.compose.screen.authentication.register.RegisterScreen
import hiof.g12.compose.screen.social.SocialScreen
import hiof.g12.compose.screen.authentication.welcome.WelcomeScreen
import hiof.g12.compose.screen.diary.diarydetail.DiaryDetailScreen
import hiof.g12.compose.screen.hobbies.HobbiesDetail.HobbiesDetailScreen
import hiof.g12.compose.screen.menu.MenuScreen
import hiof.g12.compose.screen.user.UserScreen
// Mye av logikken for bottomNav er hentet fra YouTube tutorial:
// https://www.youtube.com/watch?v=5zDMdPHCRHs&t=252s&ab_channel=AndroidWork

// Returnerer false hvis bruker er på noen av disse sidene. Denne brukes for å vise bottom navbar.
@Composable
fun validBottomNavRoute(destination: String?): Boolean {
    return destination != Screens.WelcomeScreen.name &&
            destination != Screens.LoginScreen.name &&
            destination != Screens.RegisterScreen.name &&
            destination != Screens.MenuScreen.name
}


// App Navigation her. Har kontroll på hvor man er, og hvilke sider som er navigerbare.
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (validBottomNavRoute(currentDestination?.route)) {
                BottomNavBar(navController, currentDestination)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.WelcomeScreen.name,
            modifier = Modifier.padding(paddingValues),
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            composable(route = Screens.WelcomeScreen.name) {
                WelcomeScreen(navController = navController)
            }
            composable(route = Screens.LoginScreen.name) {
                LoginScreen(loggedIn = {navController.navigate(Screens.HomeScreen.name)})
            }
            composable(route = Screens.RegisterScreen.name) {
                RegisterScreen(loggedIn = {navController.navigate(Screens.HomeScreen.name)})
            }
            composable(route = Screens.MenuScreen.name) {
                MenuScreen(navController = navController)
            }
            composable(route = Screens.HomeScreen.name) {
                HomeScreen(navController = navController)
            }
            composable(route = Screens.HobbiesScreen.name) {
                HobbiesScreen(navController = navController)
            }
            composable(route = Screens.HobbiesDetailScreen.name + "/{hobbyId}") { backStackEntry ->
                val hobbyId = backStackEntry.arguments?.getString("hobbyId")
                HobbiesDetailScreen(navController = navController, hobbyId = hobbyId)
            }
            composable(route = Screens.AddHobbyScreen.name) {
                AddHobbyScreen(navController = navController)
            }
            composable(route = Screens.MyDiaryScreen.name) {
                MyDiaryScreen(navController = navController)
            }
            composable(route = Screens.DiaryDetailScreen.name + "/{diaryId}") { backStackEntry ->
                val diaryId = backStackEntry.arguments?.getString("diaryId")
                DiaryDetailScreen(navController = navController, diaryId = diaryId)
            }
            composable(route = Screens.CalendarScreen.name) {
                CalendarScreen(navController = navController)
            }
            composable(route = Screens.SocialScreen.name) {
                SocialScreen(navController = navController)
            }
            composable(route = Screens.UserScreen.name) {
                UserScreen(navController = navController)
            }
        }
    }
}