package hiof.g12.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Lager en Spacer komponent som kan gjenbrukes
// Fordi space 10.dp brukes ofte i appen for å skille mellom komponentene, setter jeg 10 som default.
@Composable
fun SpacerComponent(space: Int = 10) {
    Spacer(modifier = Modifier.height(space.dp))
}