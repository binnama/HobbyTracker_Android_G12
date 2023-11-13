package hiof.g12.compose.screen.authentication.login

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.ClickableLoginTextComponent
import hiof.g12.component.DividerTextComponent
import hiof.g12.component.IconStart
import hiof.g12.component.MyTextFieldComponent
import hiof.g12.component.PasswordTextFieldComponent
import hiof.g12.component.UnderlinedNormalTextComponent
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.ui.theme.BackGroundColor


@Composable
fun LoginScreen (navController: NavController) {

    val viewModel: LoginViewModel = viewModel()

    Surface (
        color = BackGroundColor,
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconStart()
            Spacer(modifier = Modifier.height(50.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_email),
                value = viewModel.email.value,
                onValueChange = { newValue -> viewModel.email.value = newValue },
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_password),
                value = viewModel.password.value,
                onValueChange = { newValue -> viewModel.password.value = newValue },
            )

            Spacer(modifier = Modifier.height(10.dp))
            UnderlinedNormalTextComponent(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.height(10.dp))
            ButtonStartComponent(
                value = stringResource(id = R.string.login),
                onClick = {
                    viewModel.loginUser() { onComplete ->
                        if (onComplete) {
                            // Når login er vellykket så navigerer vi til Home screen.
                            navController.navigate(Screens.HomeScreen.name) {
                                // Fjerner vekk navStack slik at brukere ikke kan gå tilbake til login
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        } else {
                            // Her kan vi hente feilmeldingen fra LoginViewModel til å vise i TOAST
                        }
                    }
                })

            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()

            Spacer(modifier = Modifier.height(20.dp))
            ClickableLoginTextComponent(tryingToLogin = false,  onTextSelected = {})

        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen()
}
*/