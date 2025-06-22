package com.emprestaai

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
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

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmprestaAiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(
//                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color(0xFFFFD83C)) // Cor #ffd83c
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }

    val activity = LocalContext.current as? Activity
    Column(
        modifier = modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Logotipo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 40.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Cadastre-se",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = modifier.size(10.dp))

        NameField(value = name, onValueChange = { name = it })

        Spacer(modifier = Modifier.size(8.dp))

        EmailField(value = email, onValueChange = { email = it })

        Spacer(modifier = Modifier.size(8.dp))

        PasswordField(value = password, onValueChange = { password = it })

        Spacer(modifier = Modifier.size(8.dp))

        RepeatPasswordField(value = repeatPassword, onValueChange = { repeatPassword = it })

        Spacer(modifier = modifier.size(5.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    activity?.startActivity(
                        Intent(activity, LoginActivity::class.java).setFlags(
                            FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                    Toast.makeText(activity, "Cadastro realizado!", Toast.LENGTH_LONG).show()
                    activity?.finish()
                },
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty() && password == repeatPassword,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp, end=3.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Confirmar Cadastro", textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    activity?.finish()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp, end=3.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Já tem uma conta? ", textAlign = TextAlign.Center)
                Text(text = "Entre", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            }
        }
    }
}