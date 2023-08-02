package com.example.note_application.screens

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note_application.R
import com.example.note_application.component.NoteButton
import com.example.note_application.component.NoteTextField
import com.example.note_application.db.DataSourceNote
import com.example.note_application.models.Note
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteScreen(
    note : List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit

){

    val keyboardControl = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val noteTitle = remember{
        mutableStateOf("")
    }

    val description = remember{
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
            NoteButton(text = "Save", onClick = {
                if (noteTitle.value.isNotBlank() && description.value.isNotBlank()) {
                    onAddNote(Note(title = noteTitle.value, description = description.value))
                    noteTitle.value = ""
                    description.value = ""
                    keyboardControl?.hide()
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Input fields cannot be blank", Toast.LENGTH_SHORT).show()
                }
            })
            Divider()

            LazyColumn{
                items(note){
                        note -> NoteRow(note = note, onNoteClicked = {onRemoveNote(note)})
                }
            }
        }
        

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFF3B5F3),
        tonalElevation = 6.dp) {
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
                style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM yy hh:mm")),
                style = MaterialTheme.typography.bodySmall)


        }


    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(note = DataSourceNote().getNotes(),{},{})
}