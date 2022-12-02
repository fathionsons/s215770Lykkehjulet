package com.fathi.s215770lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fathi.s215770lykkehjulet.ui.theme.LykkeHjulScreen
import com.fathi.s215770lykkehjulet.ui.theme.S215770LykkehjuletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S215770LykkehjuletTheme {
                // start the game
                val viewModel by viewModels<GameViewModel>()
                LykkeHjulScreen(viewModel = viewModel)
            }
        }
    }
}

