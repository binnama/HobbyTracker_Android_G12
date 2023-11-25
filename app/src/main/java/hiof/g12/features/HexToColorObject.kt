package hiof.g12.features

import androidx.compose.ui.graphics.Color


// Lager en funksjon som kan gjenbrukes i appen for å konvertere Hex String til Color object
// Hentet fra: parseColor https://developermemos.com/posts/using-hex-colors-compose
fun HexToColorObject(hexString: String): Color {
    fun String.toColor() = Color(android.graphics.Color.parseColor(this))
    val color = hexString.toColor()
    return color
}