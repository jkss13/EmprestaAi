package com.emprestaai.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class BottomNavRoute(val route: String) {
    object List : BottomNavRoute("list")
    object Map : BottomNavRoute("map")
}

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    data object ListButton :
        BottomNavItem("Favoritos", Icons.Default.Favorite, BottomNavRoute.List.route)

    data object MapButton :
        BottomNavItem("Mapa", Icons.Default.LocationOn, BottomNavRoute.Map.route)
}