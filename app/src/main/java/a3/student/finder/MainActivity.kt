package a3.student.finder

import a3.student.finder.ui.theme.StudentFinderTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentFinderTheme {
                SetupNavGraph(navController = rememberNavController())
            }
        }
    }
}