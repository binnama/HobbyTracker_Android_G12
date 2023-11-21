package hiof.g12.component

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import hiof.g12.R
import hiof.g12.compose.model.Hobby
import hiof.g12.compose.screen.hobbies.HobbiesViewModel
import hiof.g12.compose.screen.hobbies.HobbyItem
import hiof.g12.compose.ui.theme.*
import kotlinx.coroutines.tasks.await
/*
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
 */

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        style = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun UnderlinedNormalTextComponent(value: String) {
    Text(
        text = value,
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
    )
}

@Composable
fun HeaderTextComponent(value: String) {
    Text(
        text = value,
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        style = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun IconStart () {

    val imageModifier = Modifier
        .size(120.dp)
        .padding(24.dp)
    val hourglassIcon = painterResource(id = R.drawable.hourglass_v2)
    Image(
        painter = hourglassIcon,
        contentDescription = null,
        modifier = imageModifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent (
    labelValue: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text(text = labelValue, color = White) },
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = White,
            focusedBorderColor = White,
            focusedLabelColor = White,
            cursorColor = White,
            containerColor = InputBGColor,
        ),
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = onValueChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent (
    labelValue: String,
    value: String,
    onValueChange: (String) -> Unit) {

    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        label = { Text(text = labelValue, color = White) },
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = White,
            focusedBorderColor = White,
            focusedLabelColor = White,
            cursorColor = White,
            containerColor = InputBGColor,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = onValueChange,
        trailingIcon = {
            val iconImage = if(passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var passwordDescription = if(passwordVisible.value){
                stringResource(R.string.hide_password)
            } else {
                stringResource(R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = passwordDescription )
            }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonStartComponent (value: String,  onClick: () -> Unit, isEnabled : Boolean = true) {

    Button(onClick = onClick,
        modifier = Modifier
            //.fillMaxWidth()
            .width(350.dp)
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        //colors = ButtonDefaults.buttonColors(Color.Transparent)
        colors = ButtonDefaults.buttonColors(Color.White),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
            )
        }
    }
}

@Composable
fun CheckboxComponent(value: String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        val checkedState = remember { mutableStateOf(false) }

        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value != checkedState.value
        } )

        ClickableTextComponent(value = value)
    }
}

@Composable
fun ClickableTextComponent(value: String) {

    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsAndConditionsText = "Terms and Use"

    val annotatedString = buildAnnotatedString {
        pushStyle(SpanStyle(color = White))
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString , onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {span ->
                Log.d("ClickableTextComponent", "{$span}")

            }
    })
}


@Composable
// Denne må endres senere :)
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {

    val initialText = if (tryingToLogin) "Already have an account? "
    else "Don't have an account yet? "
    val loginText = if (tryingToLogin) "Login"
    else "Register"

    val annotatedString = buildAnnotatedString {
        pushStyle(SpanStyle(color = White))
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),

        text = annotatedString , onClick = {offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also {span ->
                    Log.d("ClickableTextComponent", "{$span}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        })
}


@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .width(10.dp)
                //.fillMaxWidth()
                .weight(1f),
            color = White,
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 16.sp,
            color = White
        )

        Divider(
            modifier = Modifier
                .width(10.dp)
                //.fillMaxWidth()
                .weight(1f),
            color = White,
            thickness = 1.dp
        )
    }
}

// Get username from the DB
@Composable
fun UserNameDisplayComponent() {
    var userName by remember { mutableStateOf("") }
    val firebaseAuth = FirebaseAuth.getInstance()
    val database: DatabaseReference = Firebase.database.reference

    val user = firebaseAuth.currentUser
    val userId = user?.uid

    if (userId != null) {
        LaunchedEffect(userId) {
            val userRef = database.child("users").child(userId)
            val snapshot = userRef.get().await()

            if (snapshot.exists()) {
                userName = snapshot.child("username").value.toString()
            }
        }
    }
    else {
        userName = "User"
    }
    Text(text = "$userName")
}

@Composable
fun UserPicture(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    Image(painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.size(60.dp)
    )
}

/*
@Composable
fun PlaceholderPieChart() {
    val entries = listOf(
        PieEntry(45f, "Skole"),
        PieEntry(10f, "Lese"),
        PieEntry(20f, "Skrive bok"),
        PieEntry(15f, "Fest"),
        PieEntry(15f, "Game"),
    )
    val dataSet = PieDataSet(entries, "Ex week")

    // Create and configure the PieChart
    PieChart(modifier = Modifier
        .fillMaxSize()
        .aspectRatio(1f)
    ) {
        val data = PieData(dataSet)
        setData(data)

        setUsePercentValues(true)
        centerText = "Week 44" // Placeholder text
        setCenterTextSize(16f)
    }
}
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbyDropDownMenuComponent() {

    var dExpanded by remember { mutableStateOf(false) }
    //val listOfHobbies
    val hobbiesList = listOf("Running", "Chess", "Gaming", "Writing")
    //val hobbies by viewModel.hobbies.collectAsState(emptyList())
    //val listHobbies = hobbies.toList()
    var dSelectedText by remember { mutableStateOf(hobbiesList[0]) }

    ExposedDropdownMenuBox(
        expanded = dExpanded,
        onExpandedChange = { dExpanded = !dExpanded }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = dSelectedText,
            onValueChange = {},
            label = { Text("Hobbies") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dExpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = dExpanded,
            onDismissRequest = { dExpanded = false }
        ) {
            hobbiesList.forEach { dSelectionOption ->
                DropdownMenuItem(
                    text = { Text(dSelectionOption) },
                    onClick = {
                        dSelectedText = dSelectionOption
                        dExpanded = false
                    }
                )
            }
        }
    }
}

// Bedre med start-time til db
// Ved stopp sendes end-time til db
@Composable
fun TimerComponent() {

    var isRunning by remember { mutableStateOf(false) }
    val timePassed: Long = 0
    var elapsedSecs by remember { mutableStateOf(timePassed) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    /*
    LaunchedEffect(hobbyTitle) {
        viewModel.savedTrackedTime(userId, hobbyTitle)
    }
     */

    Button(onClick = {}) {
        if (isRunning) {
            timer?.cancel()

        }
    }

}