package com.emprestaai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emprestaai.db.fb.FBDatabase
import com.emprestaai.ui.theme.EmprestaAiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmprestaAiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IntroPage(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color(0xFFFFD83C))
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}
