package a3.student.finder.pdfgenerator

import a3.student.finder.models.Student
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.Composable
import java.io.File
import java.io.FileOutputStream

class GeneratePDFStudentInfo(val context: Context, val studentInfo: Student) {
    private val WIDTH_PAGE = 420
    private val HEIGHT_PAGE = 594

    init {
        val doc = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(WIDTH_PAGE, HEIGHT_PAGE, 1).create()
        val page = doc.startPage(pageInfo)
        val canvas = page.canvas

        val paint = Paint()

        paint.color = Color.WHITE

        canvas.drawRect(
            0f,
            0f,
            WIDTH_PAGE.toFloat(),
            HEIGHT_PAGE.toFloat(),
            Paint().apply {
                color = Color.WHITE
            })

        paint.color = Color.BLACK
        paint.textSize = 14f
        paint.isFakeBoldText = true

        val PADDING_HORIZONTAL = 40f
        val PADDING_VERTICAL = 40f

        canvas.drawText("Student Profile", PADDING_HORIZONTAL, PADDING_VERTICAL, paint)
        canvas.drawLine(
            PADDING_HORIZONTAL,
            PADDING_VERTICAL + 16,
            WIDTH_PAGE - PADDING_HORIZONTAL,
            PADDING_VERTICAL + 16,
            paint
        )

        paint.textSize = 11f
        paint.isFakeBoldText = false

        fun drawAttr(key: String, value: String, positionY: Float) {
            canvas.drawText(
                "$key:\t$value",
                PADDING_HORIZONTAL,
                PADDING_HORIZONTAL + positionY,
                paint
            )
        }

        val startAttr = 38f
        val diff = 19f
        drawAttr("Name", studentInfo.name, startAttr)
        drawAttr("Student ID", studentInfo.id, startAttr + diff)
        drawAttr("SSN", studentInfo.ssn, startAttr + diff * 2)
        drawAttr("University", studentInfo.university, startAttr + diff * 3)
        drawAttr("Faculty", studentInfo.faculty, startAttr + diff * 4)
        drawAttr("Program", studentInfo.program, startAttr + diff * 5)
        drawAttr("Level", studentInfo.level.toString(), startAttr + diff * 6)
        drawAttr("GPA", studentInfo.GPA.toString(), startAttr + diff * 7)
        drawAttr("Grades", "", startAttr + diff * 8)


        doc.finishPage(page)
        val file = File(Environment.getExternalStorageDirectory(), "test.pdf")
        try {
            doc.writeTo(FileOutputStream(file))
            openPdfFile(context, file.path)
            Log.i("Generate PDF", file.path)
        } catch (e: Exception) {
            Log.i("Generate PDF", e.message.toString())
        } finally {
            doc.close()
        }
    }

    private fun openPdfFile(context: Context, filePath: String) {

    }
}