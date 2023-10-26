package hiof.g12.compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hiof.g12.compose.screen.HobbiesScreen
import hiof.g12.compose.screen.HomeScreen
import hiof.g12.compose.screen.MyDiaryScreen
import hiof.g12.compose.screen.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController : NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach{
                        navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == navItem.route} == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState= true
                            }
                        },
                        icon = { Icon(
                            imageVector = navItem.icon,
                            contentDescription = null
                        )},
                        label = {
                            Text(text = navItem.label)
                        })
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController ,
            startDestination = Screens.WelcomeScreen.name,
            modifier = Modifier.padding(paddingValues)) {
            composable(route = Screens.WelcomeScreen.name) {
                WelcomeScreen()
            }
            composable(route = Screens.HomeScreen.name) {
                HomeScreen()
            }
            composable(route = Screens.MyDiaryScreen.name) {
                MyDiaryScreen()
            }
            composable(route = Screens.HobbiesScreen.name) {
                HobbiesScreen()
            }
        }
    }
}