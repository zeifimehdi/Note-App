package com.example.note_application

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.note_application.screens.NoteScreen
import com.example.note_application.screens.NoteViewModel
import com.example.note_application.ui.theme.NoteApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                val noteViewModel = viewModel<NoteViewModel>()
                NoteApp(noteViewModel) }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    NoteApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteApp(noteViewModel: NoteViewModel){

    val notes = noteViewModel.noteList.collectAsState().value
    NoteScreen(note = notes ,
        onAddNote = {noteViewModel.addNote(it) },
        onRemoveNote ={ noteViewModel.removeNote(it)}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteApplicationTheme {

    }
}