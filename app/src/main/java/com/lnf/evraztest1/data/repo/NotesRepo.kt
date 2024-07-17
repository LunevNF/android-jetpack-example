package com.lnf.evraztest1.data.repo

import com.lnf.evraztest1.data.dao.NotesDao
import com.lnf.evraztest1.data.models.NoteModel
import com.lnf.evraztest1.ui.screens.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NotesRepo(_notesDao: NotesDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val notesDao: NotesDao = _notesDao

    fun addNote(note: NoteModel){
        coroutineScope.launch{
            notesDao.insert(note)
        }
    }

    fun updateNote(note: NoteModel){
        coroutineScope.launch{
            notesDao.update(note)
        }
    }

    fun getNotes() = runBlocking {
        coroutineScope.async{
            notesDao.getAll()
        }.await()
    }

    fun getNoteById(id: Int) = runBlocking {
        coroutineScope.async{
            notesDao.getById(id)
        }.await()
    }

    init {

    }
}