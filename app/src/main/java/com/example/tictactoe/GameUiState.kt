package com.example.tictactoe

data class GameUiState (
    val hintText: String = "Player 'O' Turn",
    val currentTurn: BoardCellValue = BoardCellValue.CROSS
)

enum class BoardCellValue {
    CIRCLE,
    CROSS,
    NONE
}

enum class Victory {
    ROW1,
    ROW2,
    ROW3,
    COLUMN1,
    COLUMN2,
    COLUMN3,
    DIAGONAL1,
    DIAGONAL2
}