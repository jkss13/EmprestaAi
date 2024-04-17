package com.emprestaai

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterPage(activity: Activity, modifier: Modifier = Modifier) {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordRepeatState = remember { mutableStateOf("") }
    val isRegisterEnabled = nameState.value.isNotEmpty()
            && emailState.value.isNotEmpty()
            && passwordState.value.isNotEmpty()
            && passwordRepeatState.value.isNotEmpty()
            && passwordState.value == passwordRepeatState.value
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registre-se",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text(text = "Nome completo") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "E-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(text = "Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordRepeatState.value,
            onValueChange = { passwordRepeatState.value = it },
            label = { Text(text = "Repita a senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Cadastro Ok!", Toast.LENGTH_LONG).show()
                    activity?.startActivity(
                        Intent(activity, LoginActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                },
                enabled = isRegisterEnabled
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.size(24.dp))
            Button(onClick = {
                nameState.value = "";
                emailState.value = "";
                passwordState.value = "";
                passwordRepeatState.value = "";
            }) {
                Text("Limpar")
            }
        }

    }

}