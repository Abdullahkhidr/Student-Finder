package a3.student.finder


import a3.student.finder.components.AnimatedBackgroundScreen
import a3.student.finder.components.FieldComponent
import a3.student.finder.datasource.GetStudentInfo
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.ScannerConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EnterIDScreen(navController: NavController) {
    val context = LocalContext.current
    var statusData by remember { mutableStateOf(StatusData.None) }
    AnimatedBackgroundScreen {
        Column(
            modifier = Modifier
                .padding(25.dp)
                .verticalScroll(rememberScrollState())
                .background(
                    Color(0.92f, 0.92f, 0.92f, 0.5f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 25.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var idField by remember { mutableStateOf("") }
            var value by remember { mutableStateOf(360f) }
            val degreeRotation = animateFloatAsState(
                targetValue = value,
                animationSpec = tween(3000)
            )
            LaunchedEffect(statusData) {
                while (statusData == StatusData.Loading) {
                    value += 360f
                    value %= Float.MAX_VALUE
                    delay(3000)
                }
            }

            Image(
                painterResource(id = R.drawable.ic_anu), "ANU Logo",
                modifier = Modifier
                    .size(170.dp)
                    .border(
                        7.dp,
                        if (statusData == StatusData.Failed) Color.Red else Color.Transparent,
                        shape = CircleShape
                    )
                    .graphicsLayer {
//                        rotationY = degreeRotation.value
//                        rotationX = degreeRotation.value
                        rotationZ = degreeRotation.value
                    }
            )

            FieldComponent(
                value = idField,
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {
                if (it.length < 14 && it.all { ch -> Regex("[0-9]").matches("$ch") })
                    idField = it
            }

            suspend fun getUserData() {
                statusData = StatusData.Loading
                studentInfo = GetStudentInfo(idField).getData()
                studentInfo?.let {
                    Log.i("Student Info", studentInfo.toString())
                }
                statusData = if (studentInfo == null) StatusData.Failed
                else StatusData.Success
            }

            val scanQRCode =
                rememberLauncherForActivityResult(contract = ScanCustomCode()) {
                    when (it) {
                        is QRResult.QRSuccess -> {
                            idField =
                                it.content.rawValue.toString().replace(Regex("[^0-9]"), "")
                            GlobalScope.launch {
                                getUserData()
                            }
                        }

                        is QRResult.QRMissingPermission -> {
                            Toast.makeText(
                                context,
                                "Missing Permission",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is QRResult.QRError -> {
                            Toast.makeText(
                                context,
                                it.exception.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        else -> {}
                    }
                }


            DisposableEffect(key1 = statusData) {
                if (statusData == StatusData.Success && studentInfo != null) {
                    navController.navigate(Screen.StudentInfoScreen.route)
                }
                onDispose { }
            }
            //  LoadingDialog(statusData != StatusData.Loading)
            FilledTonalButton(
                modifier = Modifier.border(2.dp, Color.Gray, shape = CircleShape),
                onClick = {
                    if (idField.isEmpty()) {
                        scanQRCode.launch(ScannerConfig.build {
                            setOverlayStringRes(R.string.msgs_scan_std_id)
                        })
                    } else {
                        GlobalScope.launch {
                            getUserData()
                        }
                    }
                }, colors = ButtonDefaults.filledTonalButtonColors(
                    contentColor = Color.DarkGray,
                    containerColor = Color(0xFFE2DAC0)
                )
            ) {
                Text(text = if (idField.isEmpty()) "Scan ID" else "Show Info")
                if (idField.isEmpty()) Spacer(modifier = Modifier.size(5.dp))
                if (idField.isEmpty()) Icon(
                    painterResource(id = R.drawable.ic_qr_code),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

