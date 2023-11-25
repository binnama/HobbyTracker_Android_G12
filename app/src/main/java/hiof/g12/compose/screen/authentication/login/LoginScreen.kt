package hiof.g12.compose.screen.authentication.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.ClickableLoginTextComponent
import hiof.g12.component.IconStart
import hiof.g12.component.MyTextFieldComponent
import hiof.g12.component.PasswordTextFieldComponent
import hiof.g12.component.SpacerComponent
import hiof.g12.component.UnderlinedNormalTextComponent
import hiof.g12.compose.ui.theme.BackGroundColor

@Composable
fun LoginScreen  (loggedIn: () -> Unit,
                  viewModel: LoginViewModel = hiltViewModel()
){

    val uiState by viewModel.uiState

    Surface (
        color = BackGroundColor,
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconStart()
            SpacerComponent(50)

            // Dersom error eksisterer, vis det.
            if (uiState.errorMessage != 0)
                Text(
                    text = stringResource(id = uiState.errorMessage),
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_email),
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) }
            )

            SpacerComponent()
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_password),
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) }
            )

            SpacerComponent()
            UnderlinedNormalTextComponent(value = stringResource(id = R.string.forgot_password))

            SpacerComponent()
            ButtonStartComponent(
                value = stringResource(id = R.string.login),
                onClick = { viewModel.onLoginClick(loggedIn)
                })

            SpacerComponent(20)
            ClickableLoginTextComponent(tryingToLogin = false,  onTextSelected = {})
        }
    }
}
