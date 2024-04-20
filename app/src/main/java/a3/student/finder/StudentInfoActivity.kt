package a3.student.finder

import a3.student.finder.components.AnimatedBackgroundScreen
import a3.student.finder.models.Student
import a3.student.finder.models.StudentGrade
import a3.student.finder.ui.theme.StudentFinderTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StudentInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentFinderTheme {
                InfoScreen()
            }
        }
    }
}

@Preview
@Composable
fun InfoScreen() {
    val studentInfo = Student(
        "2072022102",
        "303681871234572",
        "Abdullah Khidr Mohamed Abdelrahim",
        "ANU",
        "Computers and Information",
        2,
        "General Division",
        3.1f,
        grades
    )
    AnimatedBackgroundScreen {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = "Student Profile", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Divider(Modifier.padding(vertical = 10.dp), color = Color.White)
            StudentInfoAttr(key = "Name", value = studentInfo.name)
            StudentInfoAttr(key = "University", value = studentInfo.university)
            StudentInfoAttr(key = "Faculty", value = studentInfo.faculty)
            StudentInfoAttr(key = "Student ID", value = studentInfo.id)
            StudentInfoAttr(key = "SSN", value = studentInfo.ssn)
            StudentInfoAttr(key = "Program", value = studentInfo.program)
            StudentInfoAttr(key = "Level", value = studentInfo.level.toString())
            StudentInfoAttr(key = "GPA", value = studentInfo.GPA.toString())
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x99F0F0F0), shape = RoundedCornerShape(2.dp))
                    .padding(10.dp)
            ) {
                Text(text = "Grades", fontSize = 20.sp)
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    ColumnTable(title = "Index") { for (i in studentInfo.grades.indices) CellTable(i + 1) }
                    ColumnTable(title = "Code") { for (i in studentInfo.grades) CellTable(i.courseCode) }
                    ColumnTable(title = "Course Name") { for (i in studentInfo.grades) CellTable(i.courseName) }
                    ColumnTable(title = "Grade") { for (i in studentInfo.grades) CellTable(i.grade) }
                    ColumnTable(title = "Grade Description") {
                        for (i in studentInfo.grades) CellTable(
                            i.gradeDescription
                        )
                    }
                    ColumnTable(title = "Points") { for (i in studentInfo.grades) CellTable(i.points) }
                    ColumnTable(title = "Credits") { for (i in studentInfo.grades) CellTable(i.credits) }
                    ColumnTable(title = "Accountancy") { for (i in studentInfo.grades) CellTable(i.accountancy) }
                    ColumnTable(title = "Level") { for (i in studentInfo.grades) CellTable(i.level) }
                    ColumnTable(title = "Year") { for (i in studentInfo.grades) CellTable("${i.year}-${i.year + 1}") }
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

@Composable
private fun StudentInfoAttr(key: String, value: String) {
    Column(
        Modifier
            .padding(vertical = 3.dp)
            .fillMaxWidth()
            .background(Color(0x99F0F0F0), shape = RoundedCornerShape(2.dp))
            .padding(vertical = 10.dp, horizontal = 13.dp)
    ) {
        Text(text = key, color = Color.Gray, fontSize = 12.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun CellTable(value: Any, isHeader: Boolean = false) {
    Column(modifier = Modifier.padding(3.dp)) {
        Text(
            "$value",
            fontSize = 14.sp,
            fontWeight = if (isHeader) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun ColumnTable(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color(0x9AF3F3F3))
            .border(1.dp, Color.Gray)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        CellTable(title, isHeader = true)
        content()
    }
}

val grades = listOf(
    StudentGrade(2023, 3, "Software Engineering", "SWE3000", 3, "A", "Excellent", 4.0f, 90.0f),
    StudentGrade(
        2023,
        3,
        "Data Structures and Algorithms",
        "DSA2000",
        4,
        "B+",
        "Very Good",
        3.5f,
        85.0f
    ),
    StudentGrade(2023, 2, "Calculus", "MATH2010", 3, "A-", "Outstanding", 3.7f, 93.0f),
    StudentGrade(2024, 4, "Computer Networks", "CN4000", 4, "B", "Good", 3.0f, 80.0f),
    StudentGrade(2024, 4, "Operating Systems", "OS4020", 3, "A", "Excellent", 4.0f, 95.0f),
    StudentGrade(
        2023,
        1,
        "Introduction to Programming",
        "CS1010",
        3,
        "C+",
        "Satisfactory",
        2.5f,
        75.0f
    ),
    StudentGrade(2024, 2, "Linear Algebra", "MATH2020", 4, "B-", "Above Average", 2.7f, 78.0f),
    StudentGrade(2023, 4, "Senior Project", "SWE4999", 3, "A", "Excellent", 4.0f, 92.0f),
    StudentGrade(2024, 1, "English Literature", "ENG1000", 3, "B", "Good", 3.0f, 82.0f),
    StudentGrade(2024, 3, "Discrete Mathematics", "MATH3000", 3, "A-", "Outstanding", 3.7f, 90.0f),
)