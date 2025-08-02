package com.emprestaai

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                ProfileInfoPage(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(Color(0xFFFFD83C))
                )
            }
        }
    }
}

@Composable
fun ProfileInfoPage(modifier: Modifier = Modifier) {
    // Dados fictícios do perfil
    var name by remember { mutableStateOf("João da Silva") }
    var birthDate by remember { mutableStateOf("15/03/1985") }
    var cpf by remember { mutableStateOf("123.456.789-00") }
    var cep by remember { mutableStateOf("01234-567") }
    var street by remember { mutableStateOf("Rua das Flores") }
    var number by remember { mutableStateOf("123") }
    var complement by remember { mutableStateOf("Apto 101") }
    var neighborhood by remember { mutableStateOf("Jardim Primavera") }
    var city by remember { mutableStateOf("São Paulo") }
    var state by remember { mutableStateOf("SP") }
    var email by remember { mutableStateOf("joao.silva@example.com") }
    var phone by remember { mutableStateOf("(11) 98765-4321") }

    // Estados para controlar quais campos estão editáveis
    var editingName by remember { mutableStateOf(false) }
    var editingBirthDate by remember { mutableStateOf(false) }
    var editingCpf by remember { mutableStateOf(false) }
    var editingCep by remember { mutableStateOf(false) }
    var editingStreet by remember { mutableStateOf(false) }
    var editingNumber by remember { mutableStateOf(false) }
    var editingComplement by remember { mutableStateOf(false) }
    var editingNeighborhood by remember { mutableStateOf(false) }
    var editingCity by remember { mutableStateOf(false) }
    var editingState by remember { mutableStateOf(false) }
//    var editingEmail by remember { mutableStateOf(false) }
    var editingPhone by remember { mutableStateOf(false) }

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
        // Foto do perfil
//        Image(
//            painter = painterResource(id = R.drawable.img_logo), // Substitua por uma imagem de perfil
//            contentDescription = "Foto do perfil",
//            modifier = Modifier
//                .size(120.dp)
//                .padding(bottom = 16.dp)
//                .background(Color.White, CircleShape),
//            contentScale = ContentScale.Crop
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "Dados do perfil",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Seção Identificação
        Text(
            text = "Identificação",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold
        )

        EditableFieldWithLabel(
            label = "Nome Completo",
            value = name,
            onValueChange = { name = it },
            isEditing = editingName,
            onEditClick = { editingName = !editingName }
        )

        EditableFieldWithLabel(
            label = "Data de Nascimento",
            value = birthDate,
            onValueChange = { birthDate = it },
            isEditing = editingBirthDate,
            onEditClick = { editingBirthDate = !editingBirthDate }
        )

        EditableFieldWithLabel(
            label = "CPF",
            value = cpf,
            onValueChange = { cpf = it },
            isEditing = editingCpf,
            onEditClick = { editingCpf = !editingCpf }
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

        EditableFieldWithLabel(
            label = "CEP",
            value = cep,
            onValueChange = { cep = it },
            isEditing = editingCep,
            onEditClick = { editingCep = !editingCep }
        )

        EditableFieldWithLabel(
            label = "Rua",
            value = street,
            onValueChange = { street = it },
            isEditing = editingStreet,
            onEditClick = { editingStreet = !editingStreet }
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            EditableFieldWithLabel(
                label = "Número",
                value = number,
                onValueChange = { number = it },
                isEditing = editingNumber,
                onEditClick = { editingNumber = !editingNumber },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            EditableFieldWithLabel(
                label = "Complemento",
                value = complement,
                onValueChange = { complement = it },
                isEditing = editingComplement,
                onEditClick = { editingComplement = !editingComplement },
                modifier = Modifier.weight(1f)
            )
        }

        EditableFieldWithLabel(
            label = "Bairro",
            value = neighborhood,
            onValueChange = { neighborhood = it },
            isEditing = editingNeighborhood,
            onEditClick = { editingNeighborhood = !editingNeighborhood }
        )

        EditableFieldWithLabel(
            label = "Cidade",
            value = city,
            onValueChange = { city = it },
            isEditing = editingCity,
            onEditClick = { editingCity = !editingCity }
        )

        EditableFieldWithLabel(
            label = "Estado",
            value = state,
            onValueChange = { state = it },
            isEditing = editingState,
            onEditClick = { editingState = !editingState }
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

        EditableFieldWithLabel(
            label = "Email",
            value = email,
            onValueChange = { email = it },
            isEditing = false,
            onEditClick = {},
            showEditIcon = false
        )

        EditableFieldWithLabel(
            label = "Telefone",
            value = phone,
            onValueChange = { phone = it },
            isEditing = editingPhone,
            onEditClick = { editingPhone = !editingPhone }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botões de ação
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { activity?.finish() },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Cancelar")
            }

            Button(
                onClick = {
                    // Verifica se algum campo está em edição
                    val isEditing = editingName || editingBirthDate || editingCpf ||
                            editingCep || editingStreet || editingNumber || editingComplement ||
                            editingNeighborhood || editingCity || editingState || editingPhone

                    if (isEditing) {
                        // Desativa todos os campos de edição
                        editingName = false
                        editingBirthDate = false
                        editingCpf = false
                        editingCep = false
                        editingStreet = false
                        editingNumber = false
                        editingComplement = false
                        editingNeighborhood = false
                        editingCity = false
                        editingState = false
                        editingPhone = false

                        // Mostra mensagem de sucesso (substituir por lógica real depois)
                        Toast.makeText(
                            activity,
                            "Alterações salvas com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            activity,
                            "Nenhuma alteração para salvar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Salvar")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun EditableFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    showEditIcon: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth().padding(bottom = 8.dp)) {
        // Label acima do campo (não muda)
        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color.Black
        )

        // Container que envolve o TextField e o ícone
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            // TextField ocupa todo o espaço (incluindo onde está o ícone)
            androidx.compose.material3.TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                enabled = isEditing,
                singleLine = true,
                shape = RoundedCornerShape(4.dp),
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    focusedContainerColor = if (isEditing) Color.White else Color(0xFFFFE0),
                    unfocusedContainerColor = if (isEditing) Color.White else Color(0xFFFFE0),
                    disabledContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    // Mostra o ícone apenas se showEditIcon for true
                    if (showEditIcon) {
                        IconButton(
                            onClick = onEditClick,
                            modifier = Modifier.size(24.dp),
                            enabled = true // Sempre habilitado, mesmo quando o TextField não está
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = if (isEditing) Color.Black else Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}