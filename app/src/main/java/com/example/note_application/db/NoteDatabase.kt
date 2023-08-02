package com.example.note_application.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.note_application.models.Note
import com.example.note_application.utills.Converters

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase() : RoomDatabase() {

    abstract fun noteDao() : NoteDatabaseDao
}