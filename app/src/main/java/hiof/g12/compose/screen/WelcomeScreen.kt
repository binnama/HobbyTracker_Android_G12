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
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.HeaderTextComponent
import hiof.g12.component.IconStart
import hiof.g12.ui.theme.BackGroundColor

@Composable
fun WelcomeScreen () {

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

            ButtonStartComponent(value = stringResource(id = R.string.login))
            Spacer(modifier = Modifier.height(150.dp))
            ButtonStartComponent(value = stringResource(id = R.string.sign_up))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen()
}