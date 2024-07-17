package com.lnf.evraztest1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lnf.evraztest1.data.dao.NotesDao
import com.lnf.evraztest1.data.models.NoteModel


@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "my_notes.db").build()
                }
            }
            return instance!!
        }
    }
}