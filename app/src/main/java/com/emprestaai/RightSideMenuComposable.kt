package com.emprestaai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars

@Composable
fun RightSideMenu(
    onDismiss: () -> Unit,
    onMenuItemSelected: (String) -> Unit,
    selectedItem: String
) {
    // Calcula os insets do sistema
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Color(0xFF353535))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(280.dp)
        ) {
            // Espaço para a barra de status (com a mesma cor do menu)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(statusBarHeight)
                    .background(Color(0xFF353535))
            )

            // Conteúdo principal do menu
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
            ) {
                // Cabeçalho com botão de fechar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Fechar menu",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable(onClick = onDismiss)
                            .padding(12.dp)
                    )
                }

                // Restante do conteúdo do menu (igual ao seu código atual)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Fulano",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                    Divider(color = Color(0xFF969696), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                val menuItems = listOf(
                    "Perfil" to Icons.Default.Person,
                    "Mapa" to Icons.Default.LocationOn,
                    "Meus itens" to Icons.Default.List,
                    "Configurações" to Icons.Default.Settings,
                    "Ajuda" to Icons.Default.Info,
                    "Sair" to Icons.Default.Close
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                ) {
                    menuItems.forEach { (title, icon) ->
                        MenuItem(
                            title = title,
                            icon = icon,
                            isSelected = selectedItem == title,
                            onClick = { onMenuItemSelected(title) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Espaço para a barra de navegação (com a mesma cor do menu)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(navigationBarHeight)
                    .background(Color(0xFF353535))
            )
        }
    }
}

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF323C43) else Color.Transparent
    val contentColor = if (isSelected) Color(0xFFFFD83C) else Color(0xFF969696)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, CircleShape)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = contentColor,
            fontSize = 16.sp
        )
    }
}