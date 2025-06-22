package com.emprestaai

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NameField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end= 3.dp)
            .height(65.dp),
        label = {
            Text(
                text = "Nome",
                color = Color.Gray.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(4.dp)
    )
}

@Composable
fun EmailField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end= 3.dp)
            .height(65.dp),
        label = {
            Text(
                text = "Email",
                color = Color.Gray.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(4.dp)
    )

}

@Composable
fun CPFField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end= 3.dp)
            .height(65.dp),
        label = {
            Text(
                text = "CPF",
                color = Color.Gray.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(4.dp)
    )

}

@Composable
fun PasswordField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end= 3.dp)
            .height(65.dp),
        label = {
            Text(
                text = "Senha",
                color = Color.Gray.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

        ),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(4.dp)
    )
}

@Composable
fun RepeatPasswordField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end= 3.dp)
            .height(65.dp),
        label = {
            Text(
                text = "Confirmação de senha",
                color = Color.Gray.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            ),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(4.dp)
    )
}

@Composable
fun RememberMeCheckbox() {
    var checked by rememberSaveable { mutableStateOf(false) }
    val checkboxSize = 18.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { checked = !checked }
            .padding(top = 6.dp, start = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(checkboxSize)
                .background(Color.White, RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = null, // Controlado pelo Row
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Transparent,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.size(checkboxSize - 2.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Mantenha conectado",
            color = Color(0xFF424242),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 2.dp)
                .size(width = 160.dp, height = 20.dp)
        )
    }
}

@Composable
fun ForgotPasswordText() {
    val activity = LocalContext.current as? Activity

    Text(
        text = "Esqueci a senha",
        color = Color(0xFF424242),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .clickable {
//                Toast.makeText(activity, "Redirecionando para recuperação de senha", Toast.LENGTH_SHORT).show()
                 activity?.startActivity(Intent(activity, PasswordRecoveryActivity::class.java))
            }
    )
}