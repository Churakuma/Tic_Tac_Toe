package com.example.tictactoe

data class GameUiState (
    val hintText: String = "Player 'O' Turn",
    val currentTurn: BoardCellValue = BoardCellValue.CIRCLE,
    val hasWon: Boolean = false
)

enum class BoardCellValue {
    CIRCLE,
    CROSS,
    NONE
}