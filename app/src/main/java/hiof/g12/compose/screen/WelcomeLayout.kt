package hiof.g12.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R

@Composable
fun WelcomeLayout () {
    val hourglassIcon = painterResource(id = R.drawable.hourglass_v2)
    val imageModifier = Modifier.size(107.dp)
        .padding(24.dp)

    Surface (
        modifier = Modifier.padding().fillMaxSize()
    ){
        Column (
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = hourglassIcon,
                contentDescription = null,
                modifier = imageModifier,
                alignment= Alignment.Center,
                )
        }
    }
}

@Preview
@Composable
fun WelcomeLayoutPrewiev() {
    WelcomeLayout()
}