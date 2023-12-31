package hiof.g12.compose.screen.hobbies

import TopBar
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hiof.g12.component.SpacerComponent

import hiof.g12.compose.ui.theme.BackGroundColor

val Red = Color(0xFFFFC8C8)
val Blue = Color(0xFF9BC2CF)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHobbyScreen(navController: NavController, viewModel: HobbiesViewModel = hiltViewModel()) {
    var hobbyText by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("Blue") }
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar= {TopBar("Add Hobby", navController)},
        containerColor = BackGroundColor) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Text(text = "Add a new hobby", color = Color.White)
                SpacerComponent()
                TextField(value = hobbyText, onValueChange = { hobbyText = it }, label = { Text(text = "Add hobby title") })
                SpacerComponent()
                EditableExposedDropdownMenuSample(onColorSelected = { selectedColor = it })
                SpacerComponent()
                Button(
                    onClick = {
                        viewModel.addHobby(hobbyText, selectedColor)
                        navController.popBackStack()
                        Toast.makeText(context, "Hobby $hobbyText ble lagt til", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Add")
                }
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Cancel")
                }
            }
    }
}

// Tatt stor del inspirasjon fra denne koden: https://www.composables.com/material3/exposeddropdownmenubox/examples
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableExposedDropdownMenuSample(onColorSelected: (String) -> Unit) {
    val options = listOf("#6C7BAA", "#CFA616", "#007D58", "#695B74")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedOptionText,
            onValueChange = { selectedOptionText = it },
            label = { Text("Associated hobby color") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        val filteringOptions = options.filter {
            it.contains(selectedOptionText, ignoreCase = true)
        }

        if (filteringOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                filteringOptions.forEach { selectionOption ->
                    val colorInt = android.graphics.Color.parseColor(selectionOption)
                    val colorText = Color(colorInt)
                    DropdownMenuItem(
                        text = { Text(selectionOption, color = colorText)},
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            onColorSelected(selectionOption)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}