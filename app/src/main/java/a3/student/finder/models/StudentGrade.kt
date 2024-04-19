package a3.student.finder.models

data class StudentGrade(
    val year: Int,
    val level: Int,
    val courseName: String,
    val courseCode: String,
    val credits: Int,
    val grade: String,
    val gradeDescription: String,
    val points: Float,
    val accountancy: Float,
)