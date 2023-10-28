package com.example.tictactoe

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    var state by mutableStateOf(GameUiState())

    var singlePlayer by mutableStateOf(true)
        private set

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE
    )

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValueToBoard(action.cellNo)
            }
            is UserAction.PlayAgainButtonClicked -> {
                gameReset()
            }
        }
    }

    private fun gameReset() {
        boardItems.forEach {(i,_) ->
            boardItems[i] = BoardCellValue.NONE
        }
        state = state.copy(
            hintText = "Player 'O' Turn",
            currentTurn = BoardCellValue.CIRCLE,
            hasWon = false
        )
        Log.d(TAG, "RESETTING THE GAME...")
    }

    private fun addValueToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            Log.d(TAG, "This Space is not empty")
            return
        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNo] = BoardCellValue.CIRCLE
            Log.d(TAG, "CIRCLE Move played")
            if (checkForVictory(BoardCellValue.CIRCLE)) {
                Log.d(TAG, "CIRCLE has won the game")
                state = state.copy(
                    hintText = "Player 'O' Won!",
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (isBoardFull()) {
                Log.d(TAG, "This game is a draw")
                state = state.copy(
                    hintText = "Game is a Draw!"
                )
            } else if (singlePlayer) {
                Log.d(TAG, "It is now the computer's move")
                computerMove()
            } else {
                Log.d(TAG, "It is now CROSS player's turn")
                state = state.copy(
                    hintText = "Player 'X' Turn",
                    currentTurn = BoardCellValue.CROSS
                )
            }
        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardItems[cellNo] = BoardCellValue.CROSS
            if (checkForVictory(BoardCellValue.CROSS)) {
                state = state.copy(
                    hintText = "Player 'X' Won!",
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (isBoardFull()) {
                state = state.copy(
                    hintText = "Game is a Draw!"
                )
            } else {
                state = state.copy(
                    hintText = "Player 'O' Turn",
                    currentTurn = BoardCellValue.CIRCLE
                )
            }
        }
    }



    private fun computerMove() {
        val randomNumber = randomNumberGenerator()

        if (boardItems[randomNumber] != BoardCellValue.NONE) {
            Log.d(TAG, "The computer chose an incorrect square")
            computerMove()
        } else {
            boardItems[randomNumber] = BoardCellValue.CROSS

            if (checkForVictory(BoardCellValue.CROSS)) {
                Log.d(TAG, "The computer has won")
                state = state.copy(
                    hintText = "Player 'X' Won!",
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (isBoardFull()) {
                Log.d(TAG, "The computer has tied the game into a draw")
                state = state.copy(
                    hintText = "Game is a Draw!"
                )
            } else {
                Log.d(TAG, "The computer has finished it's turn")
                state = state.copy(
                    hintText = "Player 'O' Turn",
                    currentTurn = BoardCellValue.CIRCLE
                )
            }
        }
    }

    private fun randomNumberGenerator(): Int {
        return (1..9).random()
    }

    private fun checkForVictory(boardValue: BoardCellValue): Boolean {
        when {
            // Horizontal win conditions
            boardItems[1] == boardValue &&
                    boardItems[2] == boardValue &&
                    boardItems[3] == boardValue -> {
                return true
            }
            boardItems[4] == boardValue &&
                    boardItems[5] == boardValue &&
                    boardItems[6] == boardValue -> {
                        return true
                    }
            boardItems[7] == boardValue &&
                    boardItems[8] == boardValue &&
                    boardItems[9] == boardValue -> {
                        return true
                    }
            // Vertical win conditions
            boardItems[1] == boardValue &&
                    boardItems[4] == boardValue &&
                    boardItems[7] == boardValue -> {
                        return true
                    }
            boardItems[2] == boardValue &&
                    boardItems[5] == boardValue &&
                    boardItems[8] == boardValue -> {
                        return true
                    }
            boardItems[3] == boardValue &&
                    boardItems[6] == boardValue &&
                    boardItems[9] == boardValue -> {
                        return true
                    }
            // Diagonal win conditions
            boardItems[1] == boardValue &&
                    boardItems[5] == boardValue &&
                    boardItems[9] == boardValue -> {
                        return true
                    }
            boardItems[3] == boardValue &&
                    boardItems[5] == boardValue &&
                    boardItems[7] == boardValue -> {
                        return true
                    }
            else -> return false
        }
    }

    private fun isBoardFull(): Boolean {
        return !boardItems.containsValue(BoardCellValue.NONE)
    }

    fun updatePlayerMode(singlePlayer: Boolean) {
        Log.d(TAG, "Game mode has been changed")
        gameReset()
        this.singlePlayer = singlePlayer
    }
}