package hiof.g12.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R

@Composable
fun WelcomeScreen () {
    val hourglassIcon = painterResource(id = R.drawable.hourglass_v2)
    val imageModifier = Modifier.size(100.dp)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Image(painter = hourglassIcon,
            contentDescription = null,
            modifier = imageModifier
        )

        Text(text = stringResource(R.string.welcome))

        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.login) )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.sign_up) )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen()
}