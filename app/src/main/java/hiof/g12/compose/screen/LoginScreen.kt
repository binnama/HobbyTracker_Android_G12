package hiof.g12.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import hiof.g12.compose.data.login.LoginUIEvent
import hiof.g12.compose.data.login.LoginViewModel
import hiof.g12.ui.theme.BackGroundColor


@Composable
fun LoginScreen (navController: NavController,
                 loginViewModel: LoginViewModel = viewModel()
) {
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

            MyTextFieldComponent(labelValue = stringResource(id = R.string.username),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.UsernameChange(it))
                })
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.enter_password),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                })

            Spacer(modifier = Modifier.height(10.dp))
            UnderlinedNormalTextComponent(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.height(10.dp))
            ButtonStartComponent(value = stringResource(id = R.string.login),
                onButtonClicked = {
                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)

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