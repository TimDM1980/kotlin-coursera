package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import games.game2048.Game2048Initializer
import games.game2048.RandomGame2048Initializer
import games.game2048.addNewValue

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val initialPermutation = initializer.initialPermutation
        TODO("fill board I guess from left to right and from up to down")
        TODO("where to put the empty cell?")
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        TODO("check whether cells are filled with 1 till 15 and last cell empty")
    }

    override fun processMove(direction: Direction) {
        TODO("Not yet implemented")
    }

    override fun get(i: Int, j: Int): Int? {
        return board.get(board.getCell(i, j))
    }

}
