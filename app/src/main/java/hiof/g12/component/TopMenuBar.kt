package hiof.g12.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hiof.g12.R
import hiof.g12.compose.ui.theme.BackGroundColor

// MENU TOP BAR TIL APPEN UTEN TITTEL
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBar(navController: NavController) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BackGroundColor
        ),
        title = {
            Text(text = "", color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = { /*Navigering til hjem*/ }) {
                Image(painter = painterResource(id = R.drawable.hourglass_v2),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close menu",
                    tint = Color.White
                )
            }
        },
    )
}