import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import hiof.g12.compose.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemComponent(title: String, description: String?, icon: ImageVector) {
        Column {
            ListItem(
                headlineText = {
                    Text(
                        text = title
                    )
                },
                supportingText = {
                    Text(
                        text = " " + description
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector= icon,
                        contentDescription = "Icon"
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = BackGroundColor,
                    headlineColor = Color.White,
                    supportingColor = Color.White,
                    leadingIconColor = Color.White
                )
            )
            Divider()

        }
}