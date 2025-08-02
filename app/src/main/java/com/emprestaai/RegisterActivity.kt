package com.emprestaai

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color(0xFFFFD83C))
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    // Seção Identificação
    var name by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var cpf by rememberSaveable { mutableStateOf("") }

    // Seção Endereço
    var cep by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }
    var complement by rememberSaveable { mutableStateOf("") }
    var neighborhood by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var state by rememberSaveable { mutableStateOf("") }

    // Seção Contato
    var email by rememberSaveable { mutableStateOf("") }
    var confirmEmail by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }

    // Seção Segurança
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    val activity = LocalContext.current as? Activity
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Logotipo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 20.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Cadastre-se",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Seção Identificação
        Text(
            text = "Identificação",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold
        )

        TextFieldWithLabel(
            label = "Nome Completo",
            value = name,
            onValueChange = { name = it },
            enabled = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Data de Nascimento",
            value = birthDate,
            onValueChange = { birthDate = it },
            enabled = name.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "CPF",
            value = cpf,
            onValueChange = { cpf = it },
            enabled = birthDate.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Seção Endereço
        Text(
            text = "Endereço",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold
        )

        TextFieldWithLabel(
            label = "CEP",
            value = cep,
            onValueChange = { cep = it },
            enabled = cpf.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Rua",
            value = street,
            onValueChange = { street = it },
            enabled = cep.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            TextFieldWithLabel(
                label = "Número",
                value = number,
                onValueChange = { number = it },
                enabled = street.isNotEmpty(),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextFieldWithLabel(
                label = "Complemento",
                value = complement,
                onValueChange = { complement = it },
                enabled = street.isNotEmpty() && number.isNotEmpty(),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Bairro",
            value = neighborhood,
            onValueChange = { neighborhood = it },
            enabled = number.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Cidade",
            value = city,
            onValueChange = { city = it },
            enabled = neighborhood.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Estado",
            value = state,
            onValueChange = { state = it },
            enabled = city.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Seção Contato
        Text(
            text = "Contato",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold
        )

        TextFieldWithLabel(
            label = "Email",
            value = email,
            onValueChange = { email = it },
            enabled = state.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Confirme seu Email",
            value = confirmEmail,
            onValueChange = { confirmEmail = it },
            enabled = email.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Telefone",
            value = phone,
            onValueChange = { phone = it },
            enabled = confirmEmail.isNotEmpty() && confirmEmail == email
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Seção Segurança
        Text(
            text = "Segurança",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold
        )

        TextFieldWithLabel(
            label = "Senha",
            value = password,
            onValueChange = { password = it },
            enabled = phone.isNotEmpty(),
            isPassword = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "Confirme sua Senha",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            enabled = password.isNotEmpty(),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão de cadastro
        Button(
            onClick = {
                activity?.startActivity(
                    Intent(activity, TermsAndConditionsAcceptanceActivity::class.java)
                        .setFlags(FLAG_ACTIVITY_SINGLE_TOP)
                )
                activity?.finish()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = name.isNotEmpty() &&
                    birthDate.isNotEmpty() &&
                    cpf.isNotEmpty() &&
                    cep.isNotEmpty() &&
                    street.isNotEmpty() &&
                    number.isNotEmpty() &&
                    neighborhood.isNotEmpty() &&
                    city.isNotEmpty() &&
                    state.isNotEmpty() &&
                    email.isNotEmpty() &&
                    confirmEmail.isNotEmpty() &&
                    phone.isNotEmpty() &&
                    password.isNotEmpty() &&
                    confirmPassword.isNotEmpty() &&
                    email == confirmEmail &&
                    password == confirmPassword,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Confirmar Cadastro")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { activity?.finish() },
            modifier = Modifier
                .fillMaxWidth()
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

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth().padding(bottom = 8.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color.Black
        )
        NameField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            isPassword = isPassword
        )
    }
}

@Composable
fun NameField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    isPassword: Boolean = false
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = androidx.compose.material3.TextFieldDefaults.colors(
            focusedContainerColor = if (enabled) Color.White else Color.LightGray.copy(alpha = 0.5f),
            unfocusedContainerColor = if (enabled) Color.White else Color.LightGray.copy(alpha = 0.5f),
            disabledContainerColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Gray,
            disabledLabelColor = Color.Gray,
            disabledPlaceholderColor = Color.Gray
        )
    )
}