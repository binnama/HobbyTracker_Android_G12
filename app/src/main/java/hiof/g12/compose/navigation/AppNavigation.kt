package hiof.g12.compose.navigation

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
import hiof.g12.compose.screen.authentication.LoginScreen
import hiof.g12.compose.screen.diary.MyDiaryScreen
import hiof.g12.compose.screen.authentication.RegisterScreen
import hiof.g12.compose.screen.social.SocialScreen
import hiof.g12.compose.screen.authentication.WelcomeScreen


// Returnerer false hvis bruker er på noen av disse sidene. Denne brukes for å vise bottom navbar.
@Composable
fun validBottomNavRoute(destination: String?): Boolean {
    return destination != Screens.WelcomeScreen.name &&
            destination != Screens.LoginScreen.name &&
            destination != Screens.RegisterScreen.name
}


// App Navigation her. Har kontroll på hvor man er, og hvilke sider som er navigerbare.
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
                LoginScreen()
            }
            composable(route = Screens.RegisterScreen.name) {
                RegisterScreen()
            }
            composable(route = Screens.HomeScreen.name) {
                HomeScreen(navController = navController)
            }
            composable(route = Screens.HobbiesScreen.name) {
                HobbiesScreen(navController = navController)
            }
            composable(route = Screens.AddHobbyScreen.name) {
                AddHobbyScreen(navController = navController)
            }
            composable(route = Screens.MyDiaryScreen.name) {
                MyDiaryScreen(navController = navController)
            }
            composable(route = Screens.CalendarScreen.name) {
                CalendarScreen(navController = navController)
            }
            composable(route = Screens.SocialScreen.name) {
                SocialScreen(navController = navController)
            }
        }
    }
}