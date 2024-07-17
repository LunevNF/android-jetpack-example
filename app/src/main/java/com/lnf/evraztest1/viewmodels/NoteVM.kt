package com.lnf.evraztest1.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.lnf.evraztest1.data.enums.StateType
import com.lnf.evraztest1.data.models.NoteModel
import com.lnf.evraztest1.data.repo.NotesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

class NoteVM(_notesRepo: NotesRepo, note_id: Int = 0) : ViewModel() {
    private val notesRepo: NotesRepo = _notesRepo

    private val _currentNote = MutableStateFlow(NoteModel(0, "", ""))
    val currentNote: MutableStateFlow<NoteModel> = _currentNote

    fun saveNote(){
        if (currentNote.value.id > 0){
            notesRepo.updateNote(currentNote.value)
        }else{
            notesRepo.addNote(currentNote.value)
        }
    }

    init {
        if (note_id > 0){
            _currentNote.value = notesRepo.getNoteById(note_id)
        }
    }

}