package com.fathi.s215770lykkehjulet

import java.sql.Struct

sealed class GameEvents {
     object Spin: GameEvents()
     object ResetGame: GameEvents()
     class LetterChosen(val letter: String): GameEvents()
}