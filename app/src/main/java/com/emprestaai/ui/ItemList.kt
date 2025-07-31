package com.emprestaai.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emprestaai.MainViewModel
import com.emprestaai.model.Item

@SuppressLint("ContextCastToActivity")
@Composable
fun ItemPage(viewModel: MainViewModel
) {

    val itemsList = viewModel.items
    val myItems = viewModel.myItems
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity

    var showDialog by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(itemsList, key = { it.name }) { item ->
                Item(
                    item = item,
                    onClose = {
                        viewModel.remove(item)
                        Toast.makeText(activity, "Item removido: $item.name", Toast.LENGTH_LONG)
                            .show()

                    }, onClick = {
                        Toast.makeText(activity, item.name, Toast.LENGTH_LONG).show()
                    })

            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(myItems, key = { it.description!! }) { item ->
                Item(
                    item = item,
                    onClose = {
                        viewModel.remove(item)
                        Toast.makeText(activity, "Item removido: $item.name", Toast.LENGTH_LONG)
                            .show()

                    }, onClick = {
                        Toast.makeText(activity, item.name, Toast.LENGTH_LONG).show()
                    })

            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.BottomEnd)
                                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Adicionar item")
        }
    }
        if (showDialog) {
        AddItemDialog(
            onAdd = { newItem ->
                viewModel.add(newItem)
                showDialog = false
                Toast.makeText(context, "Item adicionado", Toast.LENGTH_SHORT).show()
            },
            onDismiss = { showDialog = false },
        )
    }
}

@Composable
fun Item(
    item: Item,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.Card (modifier = modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 6.dp)
                                                .clickable { onClick() }
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Rounded.FavoriteBorder,
                contentDescription = ""
            )
            Spacer(modifier = modifier.size(12.dp))
            Column(modifier = modifier.weight(1f)) {
                Text(
                    modifier = Modifier,
                    text = item.name,
                    fontSize = 24.sp
                )
                Text(
                    modifier = Modifier,
                    text = item.condition ?: "Carregando clima...",
                    fontSize = 16.sp
                )
            }
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    }

}

@Composable
fun AddItemDialog(
    onAdd: (Item) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var condition by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adicionar Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") }
                )
                OutlinedTextField(
                    value = condition,
                    onValueChange = { condition = it },
                    label = { Text("Condição") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        val item = Item(
                            name = name,
                            description = description,
                            condition = condition,
                            location = null
                        )
                        onAdd(item)
                    }
                }
            ) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}