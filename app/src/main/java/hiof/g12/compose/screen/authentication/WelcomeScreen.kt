package hiof.g12.compose.screen.authentication


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.HeaderTextComponent
import hiof.g12.component.IconStart
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.screen.hobbies.Blue
import hiof.g12.compose.viewModels.WelcomeViewModel
import hiof.g12.ui.theme.BackGroundColor

@Composable
fun WelcomeScreen (navController: NavController) {

    //Getting ViewModel
    val viewModel: WelcomeViewModel = viewModel()

    Surface (
        color = BackGroundColor,
        modifier = Modifier
            .fillMaxSize()
            //.padding(28.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            IconStart()
            Spacer(modifier = Modifier.height(70.dp))
            HeaderTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(70.dp))

            ButtonStartComponent(value = stringResource(id = R.string.login), onClick = {navController.navigate(Screens.LoginScreen.name)})
            Spacer(modifier = Modifier.height(130.dp))
            ButtonStartComponent(value = stringResource(id = R.string.sign_up), onClick = {navController.navigate(Screens.RegisterScreen.name)})

            Spacer(modifier = Modifier.height(130.dp))
            ButtonStartComponent(value = stringResource(R.string.continue_without_login), onClick = {
                viewModel.authenticateAnonymously(
                    onSuccess = {
                        navController.navigate(Screens.HomeScreen.name)
                    },
                    onFailure = {exception -> //Add Error Handling
                    }
                )
            })


        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen()
}
 */