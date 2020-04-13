package games.gameOfFifteen

import board.Cell
import board.Direction
import board.Direction.*
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        initializer.initialPermutation
                .withIndex()
                .forEach { board[convertPermutationIndexToCell(it.index)] = it.value }
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        return initializer.initialPermutation
                .sorted()
                .withIndex()
                .all {
                    board[convertPermutationIndexToCell(it.index)] == it.value
                }
    }

    override fun processMove(direction: Direction) {
        with(board) {
            val emptyCell: Cell = board.find { it == null }!!

            // if you press LEFT, you want the Cell RIGHT of the empty Cell to swap with the empty Cell
            val cellToSwap = emptyCell.getNeighbour(direction.reversed())

            cellToSwap?.let {
                set(emptyCell, get(cellToSwap))
                set(cellToSwap, null)
            }
        }
    }

    override fun get(i: Int, j: Int): Int? {
        return board.get(board.getCell(i, j))
    }

    private fun convertPermutationIndexToCell(index: Int): Cell {
        val row = (index / board.width) + 1
        val column = (index % board.width) + 1
        return Cell(row, column)
    }

}
