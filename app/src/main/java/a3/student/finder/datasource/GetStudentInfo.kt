package a3.student.finder.datasource

import a3.student.finder.models.Student
import a3.student.finder.models.StudentGrade

class GetStudentInfo(private val studentId: String) {
    private var data: Student? = null

    suspend fun getData(): Student? {
        if (data == null) {
            val doc = FirebaseHelper.getDoc("StudentInfo", studentId)
            doc?.let {
                data = Student(
                    id = doc["id"].toString(),
                    ssn = doc["ssn"].toString(),
                    name = doc["name"].toString(),
                    university = doc["university"].toString(),
                    faculty = doc["faculty"].toString(),
                    level = doc["level"].toString().toInt(),
                    program = doc["program"].toString(),
                    GPA = doc["gpa"]?.toString()?.toFloat() ?: 0.0f,
                    grades = (doc["grades"] as List<*>).map { gradeMap ->
                        StudentGrade(
                            year = (gradeMap as Map<*, *>)["year"].toString().toInt(),
                            level = (gradeMap["level"].toString().toInt()),
                            courseName = gradeMap["courseName"].toString(),
                            courseCode = gradeMap["courseCode"].toString(),
                            credits = gradeMap["credits"].toString().toInt(),
                            grade = gradeMap["grade"].toString(),
                            gradeDescription = gradeMap["gradeDescription"].toString(),
                            points = gradeMap["points"]?.toString()?.toFloat()
                                ?: 0.0f,
                            accountancy = gradeMap["accountancy"]?.toString()?.toFloat()
                                ?: 0.0f,
                        )
                    }
                )
            }
        }
        return data
    }
}
