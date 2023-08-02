package com.example.note_application.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note_application.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {

    @Query("Select * from notes_tbl")
    fun getAllNotes() : Flow<List<Note>>

    @Query("Select * from notes_tbl where id=:id")
    suspend fun getNote(id : String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("Delete from notes_tbl")
    suspend fun deleteAllNotes()

}
