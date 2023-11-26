package hiof.g12.compose.screen.menu

import TopBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hiof.g12.component.MenuTopBar
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = hiltViewModel()) {

    val isAnonymous by viewModel.isAnonymous.collectAsState(initial = true)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar= {MenuTopBar(navController = navController)},
        containerColor = BackGroundColor) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Button(
                    onClick = { navController.navigate(Screens.UserScreen.name) {

                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "My Profile")
                }
                Button(
                    onClick = { navController.navigate(Screens.HomeScreen.name) {

                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Settings")
                }
                Button(
                    onClick = { navController.navigate(Screens.HomeScreen.name) {

                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "About Us")
                }

                if (!isAnonymous){
                    Button(
                        onClick = { viewModel.onSignOutClick(navController) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                        modifier = Modifier.widthIn(min = 200.dp)
                    ) {
                        Text(text = "Log Out")
                    }
                }else {
                    Button(
                        onClick = { navController.navigate(Screens.LoginScreen.name) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                        modifier = Modifier.widthIn(min = 200.dp)
                    ) {
                        Text(text = "Log In")
                    }
                }
            }
        }
    }

