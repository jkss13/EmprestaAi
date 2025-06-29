package com.emprestaai

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.emprestaai.model.Item
import com.google.android.gms.maps.model.LatLng
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _items = getMockItems().toMutableStateList()
    val items
        get() = _items.toList()

    fun remove(item: Item) {
        _items.remove(item)
    }

    fun add(name: String, description: String, condition: String, location: LatLng) {
        _items.add(
            Item(
                name = name,
                description = description,
                condition = condition,
                location = location
            )
        )
    }
}

private fun getMockItems(): List<Item> {
    val sampleNames = listOf("Cadeira de Escritório", "Furadeira de Impacto", "Monitor Gamer 27\"", "Violão de Nylon", "Mochila de Camping 70L", "Livro de Kotlin Avançado")
    val sampleConditions = listOf("Like New", "Good", "Fair", "Used")
    val sampleDescriptions = listOf("Pouco usado, em perfeito estado.", "Funcional, com algumas marcas de uso.", "Ideal para projetos rápidos.", "Ótimo para estudantes.")

    val recifeLatLng = LatLng(-8.0578, -34.8829)

    return List(15) { i ->
        Item(
            name = "${sampleNames.random()} #${i + 1}",
            description = sampleDescriptions.random(),
            condition = sampleConditions.random(),
            location = LatLng(
                recifeLatLng.latitude + Random.nextDouble(-0.05, 0.05),
                recifeLatLng.longitude + Random.nextDouble(-0.05, 0.05)
            )
        )
    }
}