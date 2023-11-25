import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.sp
import hiof.g12.compose.ui.theme.BackGroundColor

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemComponent(title: String, description: String?, icon: ImageVector, backgroundColor: String = "#343436") {
    fun String.toColor() = Color(android.graphics.Color.parseColor(this))
    val color = backgroundColor.toColor()


        Column {
            ListItem(
                headlineText = {
                    Text(
                        text = title,
                        fontSize = 20.sp
                    )
                },
                supportingText = {
                    Text(
                        text = "" + description,
                        fontSize = 15.sp
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector= icon,
                        contentDescription = "Icon"
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = color,
                    headlineColor = Color.White,
                    supportingColor = Color.White,
                    leadingIconColor = Color.White
                )
            )
            Divider()

        }
}