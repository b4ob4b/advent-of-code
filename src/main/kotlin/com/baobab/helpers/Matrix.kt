package com.baobab.helpers

import kotlin.math.abs


/**
 * Rows<Columns<T>>
 * */
data class Matrix<T>(val matrix: List<List<T>>) {

    val nRows = matrix.size
    val nCols = matrix.first().size

    fun getValue(position: Position) = matrix[position.x][position.y]
    fun getValue(row: Int, col: Int) = matrix[row][col]

    fun getRows() = matrix
    fun getCols() = this.rotateClockWise().matrix

    fun getRow(row: Int) = getRows()[row]
    fun getCols(col: Int) = getCols()[col]

    fun flipHorizontal(): Matrix<T> {
        return matrix.reversed().toMatrix()
    }

    fun flipVertical(): Matrix<T> {
        return matrix.map { it.reversed() }.toMatrix()
    }

    fun transpose(): Matrix<T> {
        val nextMatrix = MutableList(nRows * nCols) { matrix[0][0] }.chunked(nRows).map { it.toMutableList() }

        for (row in matrix.indices) {
            for (col in matrix.first().indices) {
                nextMatrix[col][row] = matrix[row][col]
            }
        }
        return nextMatrix.toMatrix()
    }

    fun neighboursOf(row: Int, col: Int, neighbourkind: NeighbourKind): List<T> {
        return positionedNeighboursOf(row, col, neighbourkind).map { matrix[it.x][it.y] }
    }

    fun positionedNeighboursOf(position: Position, neighbourkind: NeighbourKind): List<Position> {
        val (row, column) = position
        return positionedNeighboursOf(row, column, neighbourkind)
    }

    fun positionedNeighboursOf(row: Int, col: Int, neighbourkind: NeighbourKind): List<Position> {
        val neighbours = mutableListOf<Position>()
        for (dr in -1..1) {
            for (dc in -1..1) {
                val self = (dr == 0 && dc == 0)
                val isNeighbour = when (neighbourkind) {
                    NeighbourKind.ADJECENT -> (setOf(abs(dr), abs(dc)) == setOf(0, 1))
                    NeighbourKind.DIAGONAL -> (abs(dr) == 1 && abs(dc) == 1)
                    NeighbourKind.ALL -> true
                }
                if (self || !isNeighbour) continue

                val rr = row + dr
                val cc = col + dc
                if (rr in 0 until nRows && cc in 0 until nCols) {
                    neighbours.add(Position(rr, cc))
                }
            }
        }
        return neighbours
    }

    enum class NeighbourKind { ADJECENT, DIAGONAL, ALL }

    fun rotateClockWise() = this.transpose().flipVertical()

    fun rotateCounterClockWise() = this.transpose().flipHorizontal()

    fun convertElementsToInt() = this.matrix.map { it.map { it.toString().toInt() } }.toMatrix()

    @Deprecated("please use overridden toString", replaceWith = ReplaceWith("print()"))
    fun prettyPrint() {
        matrix.forEach { row ->
            row.joinToString(" ").print()
        }
        println()
    }

    override fun toString(): String {
        return matrix.map { row ->
            row.joinToString(" ")
        }.joinToString("\n")
    }
}



