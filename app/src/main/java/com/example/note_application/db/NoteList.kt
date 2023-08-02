package com.example.note_application.db

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.note_application.models.Note

@RequiresApi(Build.VERSION_CODES.O)
class DataSourceNote{

   fun getNotes() : List<Note>{
        return listOf(
            Note(
                title = "Note 1", description = "This is my Note App"
            ), Note(
                title = "Note 2", description = "Hello"
            ), Note(
                title = "Note 3", description = "My"
            ), Note(
                title = "Note 4", description = "First"
            ), Note(
                title = "Note 5", description = "Note"
            ), Note(
                title = "Note 6", description = "is"
            ), Note(
                title = "Note 7", description = "Empty Note"
            ),
        )
    }
}
