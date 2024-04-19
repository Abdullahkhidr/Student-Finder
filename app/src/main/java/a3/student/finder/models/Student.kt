package a3.student.finder.models

data class Student(
    val id: String,
    val ssn: String,
    val name: String,
    val university: String,
    val faculty: String,
    val level: Int,
    val GPA: Float,
    val grades: List<StudentGrade>
)