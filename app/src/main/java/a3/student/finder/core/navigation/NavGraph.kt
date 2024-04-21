package a3.student.finder.core.navigation

import a3.student.finder.EnterIDScreen
import a3.student.finder.StudentInfoScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.EnterIDScreen.route) {
        composable(Screen.EnterIDScreen.route) { EnterIDScreen(navController) }
        composable(Screen.StudentInfoScreen.route) { StudentInfoScreen() }
    }
}