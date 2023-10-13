package hiof.g12.compose.sceen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.CheckboxComponent
import hiof.g12.component.IconStart
import hiof.g12.component.MyTextFieldComponent
import hiof.g12.component.PasswordTextFieldComponent
import hiof.g12.ui.theme.BackGroundColor


@Composable
fun LoginScreen () {

    Surface (
        color = BackGroundColor,
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconStart()
            Spacer(modifier = Modifier.height(70.dp))

            MyTextFieldComponent(labelValue = stringResource(id = R.string.username))
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.enter_password))
            Spacer(modifier = Modifier.height(10.dp))
            CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions))

            Spacer(modifier = Modifier.height(20.dp))
            ButtonStartComponent(value = stringResource(id = R.string.login))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen()
}
