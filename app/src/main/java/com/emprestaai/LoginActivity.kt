package com.emprestaai

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emprestaai.ui.theme.EmprestaAiTheme
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmprestaAiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPage(this)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginPage(activity: Activity, modifier: Modifier = Modifier) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val isLoginEnabled = emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem vindo(a)!",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "Digite seu e-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(text = "Digite sua senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = modifier) {
            Button(onClick = {
                Toast.makeText(activity, "Login Ok!", Toast.LENGTH_LONG).show()
                activity?.startActivity(
                    Intent(activity, MainActivity::class.java).setFlags(
                        FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            },
                enabled = isLoginEnabled
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.size(24.dp))
            Button(onClick = {
                emailState.value = "";
                passwordState.value = ""
            }) {
                Text("Limpar")
            }
        }
    }
}
