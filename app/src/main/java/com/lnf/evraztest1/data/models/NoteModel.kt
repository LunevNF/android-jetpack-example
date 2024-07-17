package com.lnf.evraztest1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель: заметка
 */
@Entity(tableName = "Notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var body: String
)