package hiof.g12.compose.screen.authentication

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
import androidx.compose.ui.tooling.preview.Preview
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
import hiof.g12.compose.navigation.Screens
import hiof.g12.compose.viewModels.LoginViewModel
import hiof.g12.compose.viewModels.RegisterViewModel
import hiof.g12.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen (navController: NavController){    //This line must be changed if we implement Hilt!

    val viewModel: RegisterViewModel = viewModel()

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

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                value = viewModel.username,
                onValueChange = { viewModel.username = it }
                )
            Spacer(modifier = Modifier.height(5.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                value = viewModel.email,
                onValueChange = { viewModel.email = it }
            )
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.enter_password),
                value = viewModel.password,
                onValueChange = { viewModel.password = it }
            )
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.confirm_password),
                value = viewModel.confirmPassword,
                onValueChange = { viewModel.confirmPassword = it }
            )
            Spacer(modifier = Modifier.height(5.dp))
            CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions))

            Spacer(modifier = Modifier.height(5.dp))
            ButtonStartComponent(value = stringResource(id = R.string.sign_up), onClick = {
                viewModel.handleRegistration()
                navController.navigate(Screens.HomeScreen.name)
            })

            Spacer(modifier = Modifier.height(15.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
            })
        }
    }
}

/*
@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
*/