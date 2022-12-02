package com.fathi.s215770lykkehjulet

import androidx.compose.ui.text.buildAnnotatedString
import androidx.lifecycle.ViewModel
import com.fathi.s215770lykkehjulet.data.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val categories: List<Category> = Category.getCategories()
    private val _uiState = MutableStateFlow(GameStates())
    val uiState = _uiState.asStateFlow()
    private val possibleResults = mutableListOf<Int>()
    init {
        initiateGame()
    }
    private fun initiateGame(){
        val category = categories.random()
        val word = category.words.random()
        _uiState.update {
            GameStates(
                category = category.name,
                RandomWord = word
            )
        }
        updateGuessedWord(chosenLetter = null)
        possibleResults.addAll(1..1001)
        possibleResults.add(1001)// bankrupt
    }
    private fun updateGuessedWord(chosenLetter: Char? = null) {
        val randomWord = uiState.value.RandomWord
        val oldGuessedWord = uiState.value.guessedWord
        val guessedWord = buildAnnotatedString {
            for (c in randomWord) {
                if ((c == randomWord.first()) or (c == randomWord.last()) or (c == chosenLetter) or (c in oldGuessedWord)) {
                    append(c)
                } else {
                    append(" _ ")
                }
            }
        }.toString()

        _uiState.update {
            it.copy(
                guessedWord = guessedWord,
            )
        }
        if (!guessedWord.contains("_")) {
            gameWon()
        }
    }
    fun handleEvent(event: GameEvents) {
        when (event) {
            is GameEvents.Spin -> {
                spinTheWheel()
            }
            is GameEvents.LetterChosen -> {
                validateChosenLetter(event.letter)
            }
            is GameEvents.ResetGame -> {
                initiateGame()
            }
        }
    }
    private fun validateChosenLetter(letter: String) {
        if (letter.isNotEmpty()) {

            _uiState.update {
                it.copy(
                    chosenLetter = letter,
                    isTypingEnabled = false
                )
            }
            val word = uiState.value.RandomWord
            val chosenLetter = letter.toCharArray()[0].lowercaseChar()

            if (word.contains(letter, ignoreCase = true)) {
                val occurrence = word.count {
                    it.equals(chosenLetter, ignoreCase = true)
                }
                uiState.value.spinResult?.let {
                    val userLives = it * occurrence
                    updateLives(userLives, "Great '$chosenLetter' present guess the next letter")
                }
                updateGuessedWord(chosenLetter)
            } else {
                updateLives(-1, "Oops!! '$chosenLetter' not present you lose a life! Spin again")
            }
        }
    }
    private fun updateLives(lives: Int, message: String) {
        var userLives = 0
        _uiState.update {
            userLives = it.userLives + lives
            it.copy(
                userLives = userLives,
                message = message,
                chosenLetter = null
            )
        }
        if (userLives == 0) {
            gameLost()
        }
    }

    private fun spinTheWheel() {
        val result = possibleResults.random()
        updateSpinResult(result)
        if (result == 1001) { // event "bankrupt" is being shown
            gameLost()
        } else {
            enableTyping()
        }
    }
    private fun enableTyping() {
        _uiState.update {
            it.copy(
                isTypingEnabled = true,
                message = "Type a Letter to guess the word"
            )
        }
    }
    private fun updateSpinResult(result: Int) {
        _uiState.update {
            it.copy(
                spinResult = result
            )
        }
    }
    private fun gameLost() {
        _uiState.update {
            it.copy(
                message = "you lost the game! Reset to play the game again",
                isTypingEnabled = false,
                isSpinEnabled = false
            )
        }
    }
    private fun gameWon() {
        _uiState.update {
            it.copy(
                message = "Greet! you won the game! Reset to play the game again",
                isTypingEnabled = false,
                isSpinEnabled = false
            )
        }
    }
}