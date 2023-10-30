package hiof.g12.compose.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.HeaderTextComponent
import hiof.g12.compose.navigation.LoginNRegister.Screen
import hiof.g12.compose.navigation.LoginNRegister.StartpagesRouter
import hiof.g12.compose.navigation.LoginNRegister.SystemBackButtonHandler
import hiof.g12.ui.theme.BackGroundColor

@Composable
fun TermsAndCondScreen() {
    Surface(
        color = BackGroundColor,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

        HeaderTextComponent(value = "PLACEHOLDERTEXT")

        Spacer(modifier = Modifier.height(30.dp))
        ButtonStartComponent(value = stringResource(R.string.back),
            onButtonClicked = {

            })
    }

    SystemBackButtonHandler {
        StartpagesRouter.navigateTo(Screen.RegisterScreen)
    }
}

@Preview
@Composable
fun TermsAndCondScreenPreview() {
    TermsAndCondScreen()
}