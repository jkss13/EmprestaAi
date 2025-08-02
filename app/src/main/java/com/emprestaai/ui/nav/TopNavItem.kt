//package com.emprestaai.ui.nav
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.ui.graphics.vector.ImageVector
//
//sealed class TopNavRoute(val route: String) {
//    object Menu : TopNavRoute("menu")
//}
//sealed class TopNavItem(
//    val title: String,
//    val icon: ImageVector,
//    val route: TopNavRoute
//) {
//    object MenuButton : TopNavItem("Menu", Icons.Default.Menu, TopNavRoute.Menu)
//}
