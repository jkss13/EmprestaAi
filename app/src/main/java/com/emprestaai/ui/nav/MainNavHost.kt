package com.emprestaai.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emprestaai.ListPage
import com.emprestaai.MapPage

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavRoute.Map.route) {
        composable(BottomNavRoute.List.route) { ListPage() }
        composable(BottomNavRoute.Map.route)  { MapPage() }

    }
}