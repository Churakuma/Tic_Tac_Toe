package com.example.tictactoe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(
    gameViewModel: GameViewModel
) {
    val state = gameViewModel.state

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Tic Tac Toe",
            style = MaterialTheme.typography.titleLarge
        )
        ButtonGrid() // TODO: Place Under the LazyVerticalGrid
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f),
            columns = GridCells.Fixed(3),
            content = {
                gameViewModel.boardItems.forEach {(cellNo, boardCellValue) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                ) {
                                    gameViewModel.onAction(
                                        UserAction.BoardTapped(cellNo)
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                visible = gameViewModel.boardItems[cellNo] != BoardCellValue.NONE,
                            enter = scaleIn(tween(1000))
                            ){
                                if (boardCellValue == BoardCellValue.NONE) {
                                    Circle()
                                } else if (boardCellValue == BoardCellValue.CROSS) {
                                    Cross()
                                }
                            }
                        }
                    }
                }
            }
        )
        ResetButton()
    }

}

@Composable
fun ResetButton() {
    Button(
        onClick = { /*TODO*/ }
    ) {
        Text(
            modifier = Modifier,
            text = "Restart",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun ButtonGrid() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BorderedBox()
            BorderedBox()
            BorderedBox()
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BorderedBox()
            BorderedBox()
            BorderedBox()
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BorderedBox()
            BorderedBox()
            BorderedBox()
        }
    }
}

@Composable
fun BorderedBox() {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Green)
            .size(80.dp)
    ) {
    }

}

@Preview
@Composable
fun PrevResetButton() {
    TicTacToeTheme {
        ResetButton()
    }
}

@Preview
@Composable
fun PrevButtonGrid() {
    TicTacToeTheme {
        ButtonGrid()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevGameScreen() {
    TicTacToeTheme {
        GameScreen(gameViewModel = GameViewModel())
    }
}