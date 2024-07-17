package com.lnf.evraztest1.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lnf.evraztest1.data.models.NoteModel
import com.lnf.evraztest1.ui.screens.NoteScreen
import com.lnf.evraztest1.ui.screens.NotesListScreen
import com.lnf.evraztest1.ui.screens.Routes
import com.lnf.evraztest1.ui.theme.MainTheme
import kotlinx.coroutines.MainScope

class MainActivity: ComponentActivity() {

    lateinit var context: Context
    val mainScope = MainScope()
    var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    InitView(navController!!)
                }
            }
        }
    }
}

@Composable
fun InitView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.NotesList.name){
        composable(Routes.NotesList.name){ NotesListScreen(navController)}
        composable("${Routes.Note.name}/{noteId}",
            arguments = listOf(navArgument("noteId"){ type = NavType.IntType})
        ){
            backStackEntry ->
            val noteId: Int? = backStackEntry.arguments?.getInt("noteId")
            noteId?.let { id ->
                NoteScreen(id)
            }
        }
    }
}