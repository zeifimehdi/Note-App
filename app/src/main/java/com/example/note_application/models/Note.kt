package com.example.note_application.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.UUID
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "notes_tbl" )
data class Note(
    @PrimaryKey
    var id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title" )
    var title: String,

    @ColumnInfo(name = "note_description")
    var description: String,

    @ColumnInfo(name = "note_entry_date")
    var entryDate: LocalDateTime = LocalDateTime.now()
){}
