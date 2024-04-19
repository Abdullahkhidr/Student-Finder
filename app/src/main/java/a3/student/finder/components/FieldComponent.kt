package a3.student.finder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FieldComponent(
    value: String,
    modifier: Modifier = Modifier,
    onChangeValue: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocus by interactionSource.collectIsFocusedAsState()
    BasicTextField(value = value,
        onValueChange = onChangeValue,
        singleLine = true,
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
            .padding(vertical = 20.dp),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .border(
                        if (isFocus) 5.dp else 0.dp,
                        Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (value.isEmpty()) "Enter Student ID" else "",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                innerTextField()
            }
        }
    )
}