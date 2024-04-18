package com.emprestaai.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emprestaai.HomePage
import com.emprestaai.ListPage
import com.emprestaai.MainActivity
import com.emprestaai.MapPage

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.HomePage.route) {
        // composable (route = NOME DESTA DESTINAÇÃO) { UI DA DESTINAÇÃO }
        composable(route = BottomNavItem.HomePage.route) {
            HomePage(mainActivity = MainActivity())
        }
        composable(route = BottomNavItem.ListPage.route) {
            ListPage()
        }
        composable(route = BottomNavItem.MapPage.route) {
            MapPage()
        }
    }
}