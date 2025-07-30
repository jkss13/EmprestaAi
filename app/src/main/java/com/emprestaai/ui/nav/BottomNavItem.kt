package com.emprestaai.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class BottomNavRoute(val route: String) {
    object Home: BottomNavRoute("home")
    object Map : BottomNavRoute("map")
    object Item : BottomNavRoute("item")
}

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    data object HomeButton :
        BottomNavItem("Home", Icons.Default.Favorite, BottomNavRoute.Home.route)

    data object MapButton :
        BottomNavItem("Mapa", Icons.Default.LocationOn, BottomNavRoute.Map.route)

    data object ItemButton :
            BottomNavItem("Items", Icons.Default.Star, BottomNavRoute.Item.route)
}