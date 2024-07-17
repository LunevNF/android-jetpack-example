package com.lnf.evraztest1.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lnf.evraztest1.R
import com.lnf.evraztest1.data.models.NoteModel
import com.lnf.evraztest1.ui.theme.MainTheme
import com.lnf.evraztest1.ui.viewmodels.NoteVM
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteScreen(noteId: Int = 0) {
    val vm: NoteVM = koinViewModel(parameters = { parametersOf(noteId) })

    val currentNoteTitle = remember{ mutableStateOf(vm.currentNote.value.title)}
    val currentNoteBody = remember{ mutableStateOf(vm.currentNote.value.body)}

    val currentNote = remember { mutableStateOf(vm.currentNote) }
    NoteMainView(
        onEditOrConfirmBtnClick = {
            vm.saveNote()
        },
        vm, currentNoteTitle, currentNoteBody
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteMainView(onEditOrConfirmBtnClick: ()-> Unit,
                 vm: NoteVM, currentNoteTitle: MutableState<String>, currentNoteBody: MutableState<String>
){
    Column(modifier = Modifier.background(Color.White)) {
        TopAppBar(
            title = { Text(text = "Заметка") },
            actions = {
                IconButton(onClick = {
                    onEditOrConfirmBtnClick.invoke()
                }) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_save),
                        contentDescription = "Создать/Сохранить",
                        modifier = Modifier
                            .height(30.dp)
                    )
                }
            },
            colors = TopAppBarDefaults
                .topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Black
                )
        )
        TextField(
            value = currentNoteTitle.value,
            placeholder = { Text(text = "Заголовок заметки...")},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Yellow,
                focusedContainerColor = Color.Cyan
            ),
            onValueChange = {
                updatedText ->
                currentNoteTitle.value = updatedText
                vm.currentNote.value.title = updatedText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        TextField(
            value = currentNoteBody.value,
            placeholder = { Text(text = "Текст заметки...")},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Yellow,
                focusedContainerColor = Color.Cyan
                ),
            onValueChange = {
                updatedText ->
                currentNoteBody.value = updatedText
                vm.currentNote.value.body = updatedText
            },
            minLines = 30,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun NoteMainViewPreview(){
    MainTheme {
        NoteScreen()
    }
}