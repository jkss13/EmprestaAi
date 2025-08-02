package com.emprestaai

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListPage() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barra superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Itens próximos",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Lista de itens com scroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            items(createMockItems()) { item ->
                ItemCard(item)
                // Linha divisória cinza sem espaçamento
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Primeira coluna - Foto
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "Item image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Segunda coluna - Detalhes
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = item.state,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Row {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = if (index < item.rating) Color(0xFFFFD700) else Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Linha com ícone de distância
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "%.1f km".format(item.distance),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }

                Row {
                    Text(
                        text = "Disponibilidade: ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = item.availability,
                        color = if (item.availability == "Disponível") Color.Green else Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Terceira coluna - Valor
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "R$ ${"%.2f".format(item.hourlyValue)}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "por hora",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

// Modelo de dados atualizado com novas propriedades lógicas
data class Item(
    val imageRes: Int,
    val title: String,
    val state: String,
    val rating: Int,
    val availability: String,
    val hourlyValue: Double,
    val distance: Float,
    val owner: String,       // Nova propriedade (não exibida no front)
    val description: String  // Nova propriedade (não exibida no front)
)

// Função para criar itens fictícios atualizada
fun createMockItems(): List<Item> {
    return listOf(
        Item(
            android.R.drawable.ic_menu_camera,
            "Câmera Profissional",
            "Novo",
            4,
            "Disponível",
            25.50,
            1.5f,
            "João Silva",
            "Câmera DSLR com lente 18-55mm, perfeita para iniciantes em fotografia"
        ),
        Item(
            android.R.drawable.ic_menu_compass,
            "Bússola Digital",
            "Usado",
            3,
            "Alugado",
            12.00,
            2.3f,
            "Maria Oliveira",
            "Bússola digital com GPS integrado e resistente à água, ideal para trilhas"
        ),
        Item(
            android.R.drawable.ic_menu_day,
            "Tenda para Camping",
            "Seminovo",
            5,
            "Disponível",
            35.75,
            0.8f,
            "Pedro Santos",
            "Tenda 4 estações para 4 pessoas, fácil montagem e material resistente"
        ),
        Item(
            android.R.drawable.ic_menu_gallery,
            "Furadeira Elétrica",
            "Usado",
            2,
            "Disponível",
            18.90,
            3.1f,
            "Ana Costa",
            "Furadeira 500W com 12 velocidades, inclui kit de brocas básicas"
        ),
        Item(
            android.R.drawable.ic_menu_manage,
            "Jogo de Ferramentas",
            "Novo",
            4,
            "Alugado",
            22.30,
            1.2f,
            "Lucas Fernandes",
            "Kit completo com 32 peças para reparos domésticos e pequenos projetos"
        ),
        Item(
            android.R.drawable.ic_menu_mapmode,
            "GPS Automotivo",
            "Seminovo",
            3,
            "Disponível",
            15.60,
            4.5f,
            "Juliana Almeida",
            "Navegador GPS com tela de 5 polegadas e atualização de mapas vitalícia"
        ),
        Item(
            android.R.drawable.ic_menu_my_calendar,
            "Projetor HD",
            "Usado",
            4,
            "Disponível",
            42.80,
            0.5f,
            "Roberto Nunes",
            "Projetor 1080p com 3000 lumens, perfeito para cinema em casa"
        ),
        Item(
            android.R.drawable.ic_menu_myplaces,
            "Mesa de Escritório",
            "Novo",
            5,
            "Alugado",
            28.00,
            2.7f,
            "Camila Ribeiro",
            "Mesa regulável em altura com acabamento em madeira maciça"
        ),
        Item(
            android.R.drawable.ic_menu_preferences,
            "Console de Jogos",
            "Seminovo",
            4,
            "Disponível",
            30.50,
            1.9f,
            "Fernando Gomes",
            "Console última geração com 2 controles e 5 jogos inclusos"
        ),
        Item(
            android.R.drawable.ic_menu_report_image,
            "Cadeira Gamer",
            "Usado",
            3,
            "Disponível",
            20.25,
            3.3f,
            "Patrícia Lima",
            "Cadeira ergonômica com apoio lombar e reclinável até 180 graus"
        )
    )
}