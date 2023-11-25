package hiof.g12.compose.screen.authentication.welcome


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.HeaderTextComponent
import hiof.g12.component.IconStart
import hiof.g12.component.SpacerComponent
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.screen.authentication.register.RegisterViewModel
import hiof.g12.compose.ui.theme.BackGroundColor

@Composable
fun WelcomeScreen (navController: NavController, viewModel: WelcomeViewModel = hiltViewModel()) {

    Surface (
        color = BackGroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            IconStart()
            SpacerComponent(50)

            HeaderTextComponent(value = stringResource(id = R.string.welcome))

            SpacerComponent(50)

            ButtonStartComponent(value = stringResource(id = R.string.login),
                onClick = {navController.navigate(Screens.LoginScreen.name)})
            SpacerComponent(30)
            ButtonStartComponent(value = stringResource(id = R.string.sign_up), onClick = {navController.navigate(Screens.RegisterScreen.name)})

            SpacerComponent(30)
            ButtonStartComponent(value = stringResource(R.string.continue_without_login), onClick = {
                viewModel.authenticateAnonymously(
                    onSuccess = {
                        navController.navigate(Screens.HomeScreen.name)
                    },
                    onFailure = {exception ->
                    }
                )
            })


        }
    }
}
