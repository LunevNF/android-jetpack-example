package com.lnf.evraztest1.ui.screens

sealed class Routes(val name: String) {

    object NotesList : Routes("NotesList")
    object Note : Routes("Note")
}