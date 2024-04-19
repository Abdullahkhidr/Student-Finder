package a3.student.finder


import a3.student.finder.components.AnimatedBackgroundScreen
import a3.student.finder.components.FieldComponent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.ScannerConfig

class EnterIdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            AnimatedBackgroundScreen {
                Column(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(
                            Color(0.92f, 0.92f, 0.92f, 0.5f),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var idField by remember { mutableStateOf("") }
                    Image(
                        painterResource(id = R.drawable.ic_anu), "ANU Logo",
                        modifier = Modifier.size(170.dp)
                    )

                    FieldComponent(
                        value = idField,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    ) {
                        if (it.length < 14 && it.all { ch -> Regex("[0-9]").matches("$ch") })
                            idField = it
                    }
                    val scanQRCode =
                        rememberLauncherForActivityResult(contract = ScanCustomCode()) {
                            when (it) {
                                is QRResult.QRSuccess -> {
                                    idField =
                                        it.content.rawValue.toString().replace(Regex("[^0-9]"), "")
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
                    FilledTonalButton(
                        modifier = Modifier.border(2.dp, Color.Gray, shape = CircleShape),
                        onClick = {
                            if (idField.isEmpty()) {
                                scanQRCode.launch(ScannerConfig.build {
                                    setOverlayStringRes(R.string.msgs_scan_std_id)
                                })
                            } else {
                                startActivity(
                                    Intent(
                                        this@EnterIdActivity,
                                        StudentInfoActivity::class.java
                                    )
                                )
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
    }
}
