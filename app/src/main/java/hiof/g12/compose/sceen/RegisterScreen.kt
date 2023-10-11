package hiof.g12.compose.sceen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen (){

    val hourglassIcon = painterResource(id = R.drawable.hourglass_v2)
    val inputSpacer = 2.dp
    val imageModifier = Modifier.size(50.dp)

    //val bc_color = Color(0x343436)
    val passwordVisibility = remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    Surface (
        //color = bc_color,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(painter = hourglassIcon,
                contentDescription = null,
                modifier = imageModifier,
                )

            Text(text = "Register")

            //Username
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Username") },
                placeholder = { Text(text = "Username") },
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(inputSpacer))

            //Email
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Email") },
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(inputSpacer))

            // Password
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
                singleLine = true,
                /*
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    )} {
                    }
                }
                 */
            )
            Spacer(modifier = Modifier.padding(inputSpacer))

            // Re-entre password
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Re-enter password") },
                placeholder = { Text(text = "Re-enter password") },
                singleLine = true
            )
            //Spacer(modifier = Modifier.padding(4.dp))

            // Denne blir visst most så lenge iconet er så enormt XD
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Sign up" )
            }

            Row {
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it})
                Text(text = "I have read and accepts Terms and Conditions",
                    //color = Color()
                    modifier = Modifier.padding(16.dp))
            }
        }

    }

    /*
    var password by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        OutlinedTextField(value = stringResource(R.string.username), onValueChange = {})
        OutlinedTextField(value = stringResource(R.string.email), onValueChange = {})
        OutlinedTextField(value = password,
            onValueChange = {password = it},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
        OutlinedTextField(value = "", onValueChange = {})


    }

     */


}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
