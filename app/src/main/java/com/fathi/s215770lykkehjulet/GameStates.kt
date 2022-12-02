package com.fathi.s215770lykkehjulet

data class GameStates (
    val userLives: Int = 5,
    val RandomWord: String = "",
    val guessedWord: String = "",
    val category: String = "",
    val message: String = "Spin the wheel to start playing LykkeHjul",
    val isTypingEnabled: Boolean = false,
    val isSpinEnabled: Boolean = true,
    val chosenLetter: String? = null,
    val spinResult: Int? = null
)