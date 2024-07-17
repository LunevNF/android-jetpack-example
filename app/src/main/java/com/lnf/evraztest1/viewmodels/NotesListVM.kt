package com.lnf.evraztest1.viewmodels

import androidx.lifecycle.ViewModel
import com.lnf.evraztest1.data.dao.NotesDao
import com.lnf.evraztest1.data.enums.StateType
import com.lnf.evraztest1.data.models.NoteModel
import com.lnf.evraztest1.data.repo.NotesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NotesListVM(_notesRepo: NotesRepo): ViewModel() {

    private val notesRepo: NotesRepo = _notesRepo

    private var _items: Flow<List<NoteModel>>
    val Items: Flow<List<NoteModel>>
        get() = _items

    init {
        _items = notesRepo.getNotes()
        CoroutineScope(Dispatchers.IO).launch {
            Items.collect { value ->
                if (value.size <= 0) {
                    _notesRepo.addNote((NoteModel(1, "Тест заголовка", "Тест тела")))
                    _notesRepo.addNote((NoteModel(2, "Тест заголовка2", "Тест тела2")))
                    _items = notesRepo.getNotes()
                }
            }
        }
    }
}