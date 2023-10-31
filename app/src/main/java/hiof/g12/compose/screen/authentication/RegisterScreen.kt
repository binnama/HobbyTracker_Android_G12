package hiof.g12.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
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
import hiof.g12.component.CheckboxComponent
import hiof.g12.component.ClickableLoginTextComponent
import hiof.g12.component.DividerTextComponent
import hiof.g12.component.IconStart
import hiof.g12.component.MyTextFieldComponent
import hiof.g12.component.PasswordTextFieldComponent
import hiof.g12.compose.data.register.RegisterViewModel
import hiof.g12.compose.data.register.RegisterUIEvent
import hiof.g12.compose.navigation.LoginNRegister.Screen
import hiof.g12.compose.navigation.LoginNRegister.StartpagesRouter
import hiof.g12.compose.navigation.LoginNRegister.SystemBackButtonHandler
import hiof.g12.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen (navController: NavController,
                    registerViewModel: RegisterViewModel = viewModel()
){
    Surface (
        color = BackGroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconStart()
            Spacer(modifier = Modifier.height(50.dp))

            MyTextFieldComponent(labelValue = stringResource(id = R.string.username),
                onTextSelected = {
                    registerViewModel.onEvent(RegisterUIEvent.UsernameChanged(it))

                },
                errorStatus = registerViewModel.registrationUIState.value.usernameError
            )
            Spacer(modifier = Modifier.height(5.dp))
            MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                onTextSelected = {
                    registerViewModel.onEvent(RegisterUIEvent.UsernameChanged(it))
                },
                errorStatus = registerViewModel.registrationUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.enter_password),
                onTextSelected = {
                    registerViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                },
                errorStatus = registerViewModel.registrationUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(5.dp))
            CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    StartpagesRouter.navigateTo(Screen.TermsAndCondScreen)
                })

            Spacer(modifier = Modifier.height(5.dp))
            ButtonStartComponent(value = stringResource(id = R.string.sign_up),
                onButtonClicked = {
                    registerViewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                })

            Spacer(modifier = Modifier.height(15.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                StartpagesRouter.navigateTo(Screen.LoginScreen)
            })
        }
    }

    SystemBackButtonHandler {
        StartpagesRouter.navigateTo(Screen.WelcomeScreen)
    }
}

/*
@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
*/