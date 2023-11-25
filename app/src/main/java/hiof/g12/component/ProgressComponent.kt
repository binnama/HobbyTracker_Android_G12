package hiof.g12.component

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// Hentet fra: https://developer.android.com/jetpack/compose/components/progress
@Composable
fun ProgressComponent() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = Color.White
    )
}