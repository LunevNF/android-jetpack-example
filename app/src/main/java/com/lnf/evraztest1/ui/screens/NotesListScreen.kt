package com.lnf.evraztest1.ui.screens

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.navArgument
import com.lnf.evraztest1.R
import com.lnf.evraztest1.data.models.NoteModel
import com.lnf.evraztest1.data.repo.NotesRepo
import com.lnf.evraztest1.ui.theme.MainTheme
import com.lnf.evraztest1.viewmodels.NotesListVM
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.koinViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.get


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(navController: NavController, vm: NotesListVM = koinViewModel()) {
    CompositionLocalProvider{
        MainNotesListView(
            onNewNoteBtnClick = {
                navController.navigate("${Routes.Note.name}/0")
            }, navController, vm)
    }
}

@ExperimentalMaterial3Api
@Composable
fun MainNotesListView(onNewNoteBtnClick: ()-> Unit,
                      navController: NavController, vm: NotesListVM){
    Column(modifier = Modifier.background(Color.White)) {
        TopAppBar(
            title = { Text(text = "Заметки") },
            actions = {
              IconButton(onClick = {
                  onNewNoteBtnClick.invoke()
              }) {
                  Image(
                     imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                     contentDescription = "",
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
        NotesLazyColumn(navController, vm)
    }
}

@Composable
fun NotesLazyColumn(navController: NavController, vm: NotesListVM) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(runBlocking { vm.Items.first() }){
            item: NoteModel ->
            Column(
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.Note.name}/${item.id}")
                }) {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Text(text = item.body, maxLines = 3)
            }
        }
    }
}

@Preview
@Composable
fun NotesListScreenPreview(){
    MainTheme {
        NotesListScreen(navController = rememberNavController(), vm = koinViewModel())
    }
}