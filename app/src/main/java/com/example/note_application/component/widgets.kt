package com.example.note_application.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine : Int =1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit ={}
        )
{
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(text = label)},
        maxLines = maxLine,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),

        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        )
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text : String,
    onClick : () -> Unit,
    enabled : Boolean = true
){
    Button(onClick = onClick,
    modifier = modifier,
    shape = CircleShape,
    ) {

        Text(text = text)
    }
}