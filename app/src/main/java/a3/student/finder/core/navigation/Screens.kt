package a3.student.finder.core.navigation

sealed class Screen(val route: String) {
    data object EnterIDScreen : Screen("enter_id_screen")
    data object StudentInfoScreen : Screen("student_info_screen")

}