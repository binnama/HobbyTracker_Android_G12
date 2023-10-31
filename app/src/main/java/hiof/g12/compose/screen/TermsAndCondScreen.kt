package hiof.g12.compose.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hiof.g12.R
import hiof.g12.component.ButtonStartComponent
import hiof.g12.component.HeaderTextComponent
import hiof.g12.component.NormalTextComponent
import hiof.g12.compose.navigation.LoginNRegister.Screen
import hiof.g12.compose.navigation.LoginNRegister.StartpagesRouter
import hiof.g12.compose.navigation.LoginNRegister.SystemBackButtonHandler
import hiof.g12.ui.theme.BackGroundColor

@Composable
fun TermsAndCondScreen() {
    Surface(
        color = BackGroundColor,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

        HeaderTextComponent(value = "PLACEHOLDERTEXT")
        Spacer(modifier = Modifier.height(30.dp))
        NormalTextComponent(
            value =
            """
               Security: 
 
We here at HobbyTracker will not be responsible for any breach of  security. You are willingly choosing to upload private information on the internett, when you clearly should know better. Alas, this is not how the world works, so we have hired the best security experts and lawyers to help keep your data safe with us. 

 Since you just will see a lot of text and then press “I Agree”, we will be taking some liberties in this Contract. When you sign this document, you agree to give us your dog and or any other pets you might have. You will also be signing over your soul, which will be kept in a drawer at HQ. There is only one way to get out of this contract, which is by joining the Republican party. If you do that, we will cut all ties with you as a customer and release youre pets into the wild. 

Privacy: 

 By agreeing to our terms, you will get full access to our supert good privacy agreement. Your data is safe and will never be sold to a third party*. This should let you use our with the peace of mind you deserve. 

*: This does not apply if we ever where to change our minds or if we are offered anything worth more than a dinner at an upscale resturant.  

 
Agreement 

By pressing “I Agree”, we will take that as a “I have read this entire dokument and feel that this is a great deal”.  

We will have someone stopping by to pick up your pets and giving you all the dokuments partaining to the sale of soul. 

If you on the otherhand 
            """
        )

        Spacer(modifier = Modifier.height(30.dp))
        ButtonStartComponent(value = stringResource(R.string.back),
            onButtonClicked = {

            })
    }

    SystemBackButtonHandler {
        StartpagesRouter.navigateTo(Screen.RegisterScreen)
    }
}

@Preview
@Composable
fun TermsAndCondScreenPreview() {
    TermsAndCondScreen()
}