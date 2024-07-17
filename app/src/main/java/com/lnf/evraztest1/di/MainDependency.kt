package com.lnf.evraztest1.di

import com.lnf.evraztest1.data.AppDatabase
import com.lnf.evraztest1.data.dao.NotesDao
import com.lnf.evraztest1.data.repo.NotesRepo
import com.lnf.evraztest1.ui.viewmodels.NoteVM
import com.lnf.evraztest1.viewmodels.NotesListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module{
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().notesDao() }
}

val repoModule = module {
    fun provideNotesRepository(dao: NotesDao): NotesRepo {
        return NotesRepo(dao)
    }

    single { provideNotesRepository(get()) }
}

val viewModelModule = module {
    viewModel { NotesListVM(get()) }
    viewModel { NoteVM(get(), get()) }
}