package hiof.g12.compose.sceen

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R


// https://rrtutors.com/tutorials/how-to-create-compose-login-registration-pages
// https://www.youtube.com/watch?v=PeUERQJnHdI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (login: () -> Unit,
                 modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf(TextFieldValue()) }
    val usernameErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            value = username.value,
            onValueChange = {
                if (usernameErrorState.value) {
                    usernameErrorState.value = false
                }
            username.value = it
        },
            isError = usernameErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.enter_username))
            }
        )
        if (usernameErrorState.value) {
            Text(text = stringResource(R.string.required), color = Color.Red)
        }

        Spacer(Modifier.size(16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            /*
            value = password,
            onValueChange = { password = it},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
             */
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            isError = passwordErrorState.value,
            modifier = Modifier.fillMaxSize(),

            label = {
                Text(text = stringResource(R.string.enter_password))
            },

            /*
            trailingIcon = {
                IconButton(onClick = {passwordVisibility.value = !passwordVisibility.value})
                {
                    Icon(
                        imageVector = if(passwordVisibility.value) Icons.Default.Close
                        else Icons.Default.Check
                    )
                }
            }
             */
        )

        if (passwordErrorState.value) {
            Text(text = stringResource(id = R.string.required))
        }

        Spacer(Modifier.size(16.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }
/*
        Button(
            onClick = { login() },
            {
                content = {
                    Text(text = "Login")
                }
                )
        }
 */

    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen({})
}
