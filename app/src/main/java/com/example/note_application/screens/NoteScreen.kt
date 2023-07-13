package com.example.note_application.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note_application.R
import com.example.note_application.component.NoteButton
import com.example.note_application.component.NoteTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(){

    var noteTitle = remember{
        mutableStateOf("")
    }

    var description = remember{
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name), style = TextStyle(Color.White))
        } , actions = {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Note" , tint = Color.White)
        }, colors = TopAppBarDefaults.mediumTopAppBarColors(
            Color.Magenta
        )
        )

        // Content

        Column(modifier = Modifier.fillMaxWidth(),
        Arrangement.Center,
        Alignment.CenterHorizontally) {

            NoteTextField(text = noteTitle.value , label = "Title", onTextChange ={
                if(it.all { char ->
                char.isLetter() || char.isWhitespace() }){
                    noteTitle.value = it
                }
            } )
            NoteTextField(text = description.value , label = "Description", onTextChange ={
                if(it.all { char ->
                        char.isLetter() || char.isWhitespace() }){
                    description.value = it
                }
            } )
            NoteButton(text = "Save", onClick = { /*TODO*/ })

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen()
}