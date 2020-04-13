package board

import board.Direction.*
import kotlin.IllegalArgumentException

fun createSquareBoard(width: Int): SquareBoard = BoardImpl<Any>(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = BoardImpl(width)

class BoardImpl<T>(override val width: Int) : SquareBoard, GameBoard<T> {

    private val cells = mutableListOf<Cell>()
    private val values: MutableMap<Cell, T?> = mutableMapOf()

    init {
        for (i in 1..width) {
            for (j in 1..width) {
                val cell = Cell(i, j)
                cells.add(cell)
                values[cell] = null
            }
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return cells.filter { it == Cell(i, j) }
                .firstOrNull()
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException()
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.mapNotNull { it -> getCellOrNull(i, it) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.mapNotNull { it -> getCellOrNull(it, j) }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction) {
            UP -> getCellOrNull(this.i-1, this.j)
            DOWN -> getCellOrNull(this.i+1, this.j)
            LEFT -> getCellOrNull(this.i, this.j-1)
            RIGHT -> getCellOrNull(this.i, this.j+1)
        }
    }

    override fun get(cell: Cell): T? {
        return values[cell]
    }

    override fun set(cell: Cell, value: T?) {
        values[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return values.filterValues(predicate)
                .keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return values.filterValues(predicate)
                .keys
                .firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return values.values.any(predicate)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return values.values.all(predicate)
    }

}