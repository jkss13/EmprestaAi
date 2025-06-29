package com.emprestaai

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emprestaai.model.Item
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@SuppressLint("ContextCastToActivity")
@Composable
fun ListPage(viewModel: MainViewModel
) {

    val itemList = viewModel.items
    val activity = LocalContext.current as? Activity

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(itemList, key = { it.name }) { item ->
            ItemRow(item = item,
                onClose = {
                    viewModel.remove(item)
                    Toast.makeText(activity, "Item removido: ${item.name}", Toast.LENGTH_LONG).show()
                },
                onClick = {
                    Toast.makeText(activity, "Detalhes de: ${item.name}", Toast.LENGTH_LONG).show()
                })
        }
    }
}

@Composable
fun ItemRow(
    item: Item,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Ícone do item",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            item.description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp
                )
            }

            item.condition?.let {
                Text(
                    text = "Condição: $it",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Remover item")
        }
    }
}