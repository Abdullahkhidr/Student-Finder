package a3.student.finder.core

import a3.student.finder.models.Student
import a3.student.finder.models.StudentGrade

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

val student = Student(
    "123",
    "210545518540572",
    "Mostafa Khalifa",
    "ANU",
    "Computers and Information",
    1,
    "General Division",
    3.8f,
    grades
)