import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hiof.g12.ui.theme.BackGroundColor

// TOPBAR TIL APPEN MED EGENDEFINERT TITTEL
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BackGroundColor
        ),
        title = {
            Text(text = title, color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = { /*Navigering til hjem*/ }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Localized description",
                    tint = Color.White
                    )
            }
        },
        actions = {
            IconButton(onClick = { /*Navigering til meny*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
        },
        )
}