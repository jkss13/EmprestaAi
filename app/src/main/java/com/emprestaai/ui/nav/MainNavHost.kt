package com.emprestaai.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emprestaai.HomePage
import com.emprestaai.ListPage
import com.emprestaai.MainViewModel
import com.emprestaai.MapPage
import com.emprestaai.ui.ItemPage

@Composable
fun MainNavHost(navController: NavHostController,
                viewModel: MainViewModel) {
    NavHost(navController, startDestination = BottomNavRoute.Map.route) {
        composable(BottomNavRoute.Home.route) { HomePage() }
        composable(BottomNavRoute.Map.route)  { MapPage(viewModel) }
        composable(BottomNavRoute.Item.route){ ItemPage( viewModel) }
    }
}