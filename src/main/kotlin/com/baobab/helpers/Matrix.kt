package com.baobab.helpers


/**
 * Rows<Columns<T>>
 * */
data class Matrix<T>(val matrix: List<List<T>>) {

    val nRows = matrix.size
    val nCols = matrix.first().size

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

    fun rotateClockWise() = this.transpose().flipVertical()

    fun rotateCounterClockWise() = this.transpose().flipHorizontal()

    fun convertElementsToInt() = this.matrix.map { it.map { it.toString().toInt() } }.toMatrix()

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



