package com.emprestaai

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emprestaai.ui.theme.EmprestaAiTheme

class PasswordRecoveryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmprestaAiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PasswordRecoveryPage(
//                        name = "Android",
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(Color(0xFFFFD83C))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordRecoveryPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }

    val activity = LocalContext.current as? Activity
    Column(
        modifier = modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(40.dp))

        Text(
            text = "Recupere a sua senha",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = "Insira o e-mail cadastrado em nossa plataforma. Um link de redefinição de senha será enviado para você.",
            color = Color(0xFF424242),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.size(40.dp))

        EmailField(value = email, onValueChange = { email = it })

        Spacer(modifier = Modifier.size(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    Toast.makeText(activity, "E-mail enviado, verifique sua caixa de entrada.", Toast.LENGTH_LONG).show()
                    activity?.finish()
                },
                enabled = email.isNotEmpty(),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Enviar", textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.size(200.dp))

        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Logotipo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 120.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
    }
}
