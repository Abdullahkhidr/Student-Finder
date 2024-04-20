package a3.student.finder.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun AnimatedBackgroundScreen(content: @Composable () -> Unit) {
    // Color Animation
    var switch by remember { mutableStateOf(true) }
    val animationFirstColor = animateColorAsState(
        targetValue = if (switch) Color(0xFFF3C7CB) else Color(0xFFCF5F6A),
        animationSpec = tween(2000)
    )
    val animationSecondColor = animateColorAsState(
        targetValue = if (!switch) Color(0xFFE2DAC0) else Color(0xFFD6CC7F),
        animationSpec = tween(2000)
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            switch = !switch
        }
    }

    // =======================================================

    Box(
        contentAlignment = Alignment.Center,
        content = { content() }, modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        animationSecondColor.value,
                        Color(0xFFE2D6B0),
                        animationFirstColor.value,
                    )
                )
            )
    )
}