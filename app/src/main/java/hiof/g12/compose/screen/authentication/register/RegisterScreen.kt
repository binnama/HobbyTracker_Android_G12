package hiof.g12.compose.screen.authentication.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.ClickableLoginTextComponent
import hiof.g12.component.DividerTextComponent
import hiof.g12.component.IconStart
import hiof.g12.component.MyTextFieldComponent
import hiof.g12.component.PasswordTextFieldComponent
import hiof.g12.component.SpacerComponent
import hiof.g12.compose.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen (
                    loggedIn: () -> Unit,
                    viewModel: RegisterViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState

    Surface (
        color = BackGroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconStart()
            SpacerComponent(50)


            if (uiState.errorMessage != 0)
                Text(
                    text = stringResource(id = uiState.errorMessage),
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                value = uiState.username,
                onValueChange = { viewModel.onUsernameChange(it) }
                )

            SpacerComponent()

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) }
            )

            SpacerComponent()

            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.enter_password),
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) }
            )

            SpacerComponent()

            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.confirm_password),
                value = uiState.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) }
            )

            SpacerComponent()

            ButtonStartComponent(
                value = stringResource(id = R.string.sign_up),
                onClick = { viewModel.onSignUpClick(loggedIn)
                })

            SpacerComponent()

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
            })
        }
    }
}
