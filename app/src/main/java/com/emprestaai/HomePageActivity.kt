package com.emprestaai

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.emprestaai.ui.nav.MainNavHost
import com.emprestaai.ui.theme.EmprestaAiTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.emprestaai.db.fb.FBDatabase
import com.emprestaai.ui.nav.BottomNavBar
import com.emprestaai.ui.nav.BottomNavItem
import com.emprestaai.ui.nav.BottomNavRoute
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth

class HomePageActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val fbDB = remember { FBDatabase() }
            val viewModel : MainViewModel = viewModel(
                factory = MainViewModelFactory(fbDB)
            )
            val navController = rememberNavController()
            val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
            val showSheet = remember { mutableStateOf(false) }
            val currentRoute = navController.currentBackStackEntryAsState()
            val currentDestination = currentRoute.value?.destination?.route
            val showButton = currentDestination == BottomNavRoute.Home.route
            val launcher = rememberLauncherForActivityResult(contract =
                ActivityResultContracts.RequestPermission(), onResult = {} )
            EmprestaAiTheme {
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
                            BottomNavItem.HomeButton,
                            BottomNavItem.ItemButton,
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
//                        if (showSheet.value) {
//                            ModalBottomSheet(
//                                onDismissRequest = { showSheet.value = false },
//                                sheetState = bottomSheetState,
//                                containerColor = Color(0xFFFFD83C),
//                                modifier = Modifier.fillMaxWidth().clickable { showSheet.value = false }
//                            ) {
//                                ListPage(viewModel = viewModel)
//                            }
//                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? Activity
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem-vindo à HomePage!")

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            Firebase.auth.signOut()
        }) {
            Text("Sair")
        }
    }
}