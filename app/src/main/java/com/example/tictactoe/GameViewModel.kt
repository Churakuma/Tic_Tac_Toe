package com.example.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    var state by mutableStateOf(GameUiState())

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
            UserAction.PlayAgainButtonClicked -> {
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
    }

    private fun addValueToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNo] = BoardCellValue.CIRCLE
            if (checkForVictory(BoardCellValue.CIRCLE)) {
                state = state.copy(
                    hintText = "Player 'O' Won!",
                    currentTurn = BoardCellValue.CIRCLE,
                    hasWon = true
                )
            } else if (isBoardFull()) {
                state = state.copy(
                    hintText = "Game is a Draw!"
                )
            } else {
                state = state.copy(
                    hintText = "Player 'X' Turn",
                    currentTurn = BoardCellValue.CROSS
                    // TODO: Create AI Player Logic
                )
            }
        }
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
        if (boardItems.containsValue(BoardCellValue.NONE)) return false
        else return true
    }
}