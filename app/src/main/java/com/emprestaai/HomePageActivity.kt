package com.emprestaai

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.emprestaai.ui.nav.MainNavHost
import com.emprestaai.ui.theme.EmprestaAiTheme
import androidx.compose.material.icons.filled.List
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.emprestaai.ui.nav.BottomNavBar
import com.emprestaai.ui.nav.BottomNavItem
import com.emprestaai.ui.nav.BottomNavRoute

@OptIn(ExperimentalMaterial3Api::class)
class HomePageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmprestaAiTheme {
                val navController = rememberNavController()
                val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
                val showSheet = remember { mutableStateOf(false) }
                val viewModel: MainViewModel = viewModel()
                val currentRoute = navController.currentBackStackEntryAsState()
                val currentDestination = currentRoute.value?.destination?.route
                val showButton = currentDestination == BottomNavRoute.List.route
                val launcher = rememberLauncherForActivityResult(contract =
                    ActivityResultContracts.RequestPermission(), onResult = {} )

                Scaffold(
                    modifier = Modifier
//                            .padding(innerPadding)
                        .fillMaxSize()
                        .background(Color(0xFFFFD83C)),
                    topBar = {
                        TopAppBar(
                            title = { Text("Procure por um item", color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black
                            ),
                            actions = {
                                IconButton( onClick = { } ) {
                                    Icon(
                                        imageVector =
                                            Icons.Default.Menu,
                                        contentDescription = "Menu", tint = Color.White
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        val items = listOf(
                            BottomNavItem.ListButton,
                            BottomNavItem.MapButton,
                            )

                        BottomNavBar(navController = navController, items)

                    }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                    ) {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                        MainNavHost(navController = navController,
                                    viewModel = viewModel)

                        if (showSheet.value) {
                            ModalBottomSheet(
                                onDismissRequest = { showSheet.value = false },
                                sheetState = bottomSheetState,
                                containerColor = Color(0xFFFFD83C),
                                modifier = Modifier.fillMaxWidth().clickable { showSheet.value = false }
                            ) {
                                ListPage(viewModel = viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}