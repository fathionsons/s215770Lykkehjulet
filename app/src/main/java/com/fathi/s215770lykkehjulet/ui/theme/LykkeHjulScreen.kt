package com.fathi.s215770lykkehjulet.ui.theme

import android.media.MediaPlayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fathi.s215770lykkehjulet.GameEvents
import com.fathi.s215770lykkehjulet.GameViewModel
import com.fathi.s215770lykkehjulet.R

@Composable
fun LykkeHjulScreen(
    viewModel: GameViewModel,
) {
    val uiState = viewModel.uiState.collectAsState().value

//      rotation animation for the wheel
    var isAnimated by rememberSaveable {
        mutableStateOf(false)
    }

    val rotateAngle by animateFloatAsState(targetValue =
            if(isAnimated) 360F else 0F, animationSpec = tween(durationMillis = 3500 ))
//     sound effect for fun when spinning
//     the app is crushing here after making the audio you may delete it
//    val context = LocalContext.current
//    val soundEffect = MediaPlayer.create(context, R.raw.)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        //the title
        Text(
            text ="LYKKEHJULET" ,
            color =TextColor,
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif
        )
        Text(
            text ="Fathi s215770" ,
            color =Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
        )
        // lykkehjul skjold blev designet af mig Fathi s215770 via photoshop :)
                Image(painter = painterResource(id = R.drawable.lykkehjulskjold),
            contentDescription = "LykkeHjul",
            modifier = Modifier
                .scale(1f)
                .rotate(rotateAngle)
        )



        Spacer(modifier = Modifier.height(10.dp))
        //lives
        Text(
            text = "lives : ${uiState.userLives}",
            color = if (uiState.userLives >= 5) Color.Green else Color.Red,
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
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = SpinButton),
            onClick = { viewModel.handleEvent(GameEvents.Spin)
                isAnimated = !isAnimated},
            enabled = uiState.isSpinEnabled

        ) {
            Text(text = "Spin the wheel", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            onClick = { viewModel.handleEvent(GameEvents.ResetGame) }) {
            Text(text = "Reset the game", color = Color.White)
        }
    }













}