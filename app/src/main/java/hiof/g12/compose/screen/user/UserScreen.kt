package hiof.g12.compose.screen.user

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import hiof.g12.ui.theme.BackGroundColor
import TopBar
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import hiof.g12.R
import hiof.g12.component.UserNameDisplayComponent
import hiof.g12.component.UserPicture

@Composable
fun UserScreen (navController: NavController) {

    val db = FirebaseFirestore.getInstance()

    val userPicPlaceholder = painterResource(id = R.drawable.portrait_placeholder)

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = BackGroundColor
    ) {
        TopBar("UserName", navController)

        UserNameDisplayComponent()
        Spacer(modifier = Modifier.height(50.dp))
        
        UserPicture(painter = userPicPlaceholder,
            contentDescription = "User Picture",
            modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(50.dp))



    }
}