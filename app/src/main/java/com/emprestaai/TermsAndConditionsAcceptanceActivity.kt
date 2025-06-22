package com.emprestaai

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emprestaai.ui.theme.EmprestaAiTheme

class TermsAndConditionsAcceptanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmprestaAiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TermsAndConditionsAcceptancePage(
//                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color(0xFFFFD83C))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsAcceptancePage(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var isAtBottom by rememberSaveable { mutableStateOf(false) }
    val activity = LocalContext.current as? Activity

    LaunchedEffect(scrollState.value) {
        isAtBottom = scrollState.value == scrollState.maxValue
    }

    Column(
        modifier = modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Logotipo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 5.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.size(5.dp))

        Text(
            text = stringResource(R.string.terms_and_conditions_title),
            color = Color(0xFF424242),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {

            Text(
                text = stringResource(R.string.terms_and_conditions_text),
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    activity?.startActivity(
                        Intent(activity, LoginActivity::class.java).setFlags(
                            FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                    Toast.makeText(activity, "Cadastro realizado! Realize Login.", Toast.LENGTH_LONG).show()
                    activity?.finish()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp, end=3.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp),
                enabled = isAtBottom
            ) {
                Text(text = "Eu aceito", textAlign = TextAlign.Center)
            }
        }

    }
}