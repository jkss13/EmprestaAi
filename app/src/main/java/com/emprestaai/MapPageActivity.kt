package com.emprestaai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapPage() {
    var searchText by remember { mutableStateOf("") }
    val viewModel: MainViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        // caixa de pesquisa
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Ex.: Furadeira") },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

            GoogleMap (
                modifier = Modifier.fillMaxSize(),
                onMapClick = { viewModel.addCity("Cidade@${it.latitude}:${it.longitude}", "Clima", location = it) }) {

                viewModel.cities.forEach {
                    if (it.location != null) {
                        Marker( state = MarkerState(position = it.location),
                            title = it.name, snippet = "${it.location}")
                    }
                }
            }

    }
}