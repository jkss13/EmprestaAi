package com.emprestaai

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
class HomePageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            EmprestaAiTheme {
                val navController = rememberNavController()
                val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
                val showBottomSheet = remember { mutableStateOf(false) }
                val showRightMenu = remember { mutableStateOf(false) }
                val selectedMenuItem = remember { mutableStateOf("") }
                val context = LocalContext.current
//                val systemUiController = rememberSystemUiController()

//                SideEffect {
//                    // Configura as cores das barras do sistema
//                    systemUiController.setSystemBarsColor(
//                        color = Color.Transparent,
//                        darkIcons = false,
//                        isNavigationBarContrastEnforced = false
//                    )
//                }

                // Efeito para garantir que os modais não se sobreponham
                LaunchedEffect(showRightMenu.value, showBottomSheet.value) {
                    if (showRightMenu.value && showBottomSheet.value) {
                        if (bottomSheetState.isVisible) {
                            bottomSheetState.hide()
                        }
                        showBottomSheet.value = false
                    }
                }

                // Configuração da janela para evitar sobreposição com ícones do sistema
                WindowCompat.setDecorFitsSystemWindows(window, false)
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFFD83C)),
                        topBar = {
                            TopAppBar(
                                title = { Text("Procure por um item", color = Color.White) },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color.Black
                                ),
                                actions = {
                                    IconButton(onClick = {
                                        showRightMenu.value = true
                                        if (showBottomSheet.value) {
                                            showBottomSheet.value = false
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menu",
                                            tint = Color.White
                                        )
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    showBottomSheet.value = true
                                    showRightMenu.value = false
                                },
                                containerColor = Color.Black,
                                shape = CircleShape
                            ) {
                                Icon(
                                    Icons.Default.List,
                                    contentDescription = "Mostrar lista",
                                    tint = Color.White
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            MainNavHost(navController = navController)

                            // ModalBottomSheet - Agora com zIndex definido
                            if (showBottomSheet.value) {
                                ModalBottomSheet(
                                    onDismissRequest = { showBottomSheet.value = false },
                                    sheetState = bottomSheetState,
                                    containerColor = Color(0xFFFFD83C),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .zIndex(1f) // Z-index menor que o menu
                                ) {
                                    ListPage()
                                }
                            }
                        }
                    }

                    // Menu lateral direito com prioridade máxima
                    if (showRightMenu.value) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                                .clickable { showRightMenu.value = false }
                                .zIndex(2f) // Z-index maior que o modal
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                RightSideMenu(
                                    onDismiss = { showRightMenu.value = false },
                                    onMenuItemSelected = { item ->
                                        selectedMenuItem.value = item
                                        showRightMenu.value = false

                                        when (item) {
                                            "Perfil" -> {
                                                val intent = Intent(context, ProfileInfoActivity::class.java)
                                                context.startActivity(intent)
                                            }
                                            "Sair" -> {
                                                finish()
                                            }
                                            // Adicione outros casos conforme necessário
                                        }
                                    },
                                    selectedItem = selectedMenuItem.value
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}