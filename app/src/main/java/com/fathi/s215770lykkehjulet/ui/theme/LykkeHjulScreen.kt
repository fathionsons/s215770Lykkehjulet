package com.fathi.s215770lykkehjulet.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fathi.s215770lykkehjulet.GameEvents
import com.fathi.s215770lykkehjulet.GameViewModel

@Composable
fun LykkeHjulScreen(
    viewModel: GameViewModel,
) {
    val uiState = viewModel.uiState.collectAsState().value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        //the title
        Text(

            text ="LYKKEHJULET" ,
            color =TextColor,
            fontSize = 50.sp,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(30.dp))
        //lives
        Text(
            text = "lives : ${uiState.lives}",
            color = if (uiState.lives >= 5) Color.Green else Color.Red,
            fontSize = 20.sp,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Your category is :  ")
            Text(text = uiState.category)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Guessed word is :  ")
            Text(text = uiState.guessedWord)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = uiState.message,
            color = Color.Red,
            fontSize = 20.sp,
            modifier = Modifier.height(60.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif
        )
        TextField(
            value = uiState.chosenLetter ?: "",
            label = {
                Text(text = if (!uiState.isTypingEnabled) "Spin the Wheel" else "type here")
            },
            onValueChange = {
                viewModel.handleEvent(GameEvents.LetterChosen(it))
            },
            singleLine = true,
            enabled = uiState.isTypingEnabled
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Spin Results : ")
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = uiState.spinResult?.let {
                    if (it == 1001) "Bankrupt" else it.toString()
                } ?: "",
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { viewModel.handleEvent(GameEvents.Spin) },
            enabled = uiState.isSpinEnabled
        ) {
            Text(text = "Spin the wheel", color = SpinButton)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { viewModel.handleEvent(GameEvents.ResetGame) }) {
            Text(text = "Reset the game",color = SpinButton)
        }
    }













}