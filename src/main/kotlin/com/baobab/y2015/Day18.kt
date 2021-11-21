package com.baobab.y2015

import utils.Day
import utils.IO
import utils.IO.readFile

class Day18(val input: Input) : Day() {
    val initialField = Field.fromString(input.data)
    var field = initialField
    val sampleSize = input.sampleSize

    override fun part1(): Any? {
        repeat(sampleSize) {
            nextGeneration()
        }
        return field.countLights()
    }

    override fun part2(): Any? {
        field = Field.withDefect(initialField.matrix)
        repeat(sampleSize) {
            nextGeneration(true)
        }
        return field.countLights()
    }

    fun generateDefaultList(rows: Int, columns: Int) =
        generateSequence {
            generateSequence {
                0
            }.take(columns).toMutableList()
        }.take(rows).toMutableList()


    private fun printEmptyLines() {
        repeat(2) {
            println()
        }
    }

    private fun nextGeneration(part2: Boolean = false) {
        val currentField = field.matrix
        val rows = currentField.size
        val columns = currentField.first().size
        val nextfield = generateDefaultList(rows, columns)
        for (r in 0 until rows) {
            for (c in 0 until columns) {
                nextfield[r][c] = rule(currentField[r][c], field.getNeighbours(r, c))
            }
        }
        if (part2) {
            field = Field.withDefect(nextfield)
        } else {
            field = Field(nextfield)
        }
    }

    private fun Field.getNeighbours(r: Int, c: Int): Int {
        val currentField = this.matrix
        val rows = currentField.size
        val columns = currentField.first().size
        var countNeighbours = 0

        for (dr in -1..1) {
            for (dc in -1..1) {
                if (dr == 0 && dc == 0) continue
                val rr = r + dr
                val cc = c + dc
                if (rr in 0 until rows) {
                    if (cc in 0 until columns) {
                        if (this.matrix[rr][cc] == 1) {
                            countNeighbours++
                        }
                    }
                }
            }
        }
        return countNeighbours
    }

    private fun rule(currentState: Int, countNeighbours: Int): Int {
//        A light which is on stays on when 2 or 3 neighbors are on, and turns off otherwise.
//        A light which is off turns on if exactly 3 neighbors are on, and stays off otherwise.
        if (currentState == 1 && countNeighbours in 2..3) return 1
        if (currentState == 0 && countNeighbours == 3) return 1
        return 0
    }
}

data class Field(
    val matrix: List<List<Int>>,
) {
    companion object {
        fun fromString(string: String): Field {
            val rows = string.split("\n")
            return Field(
                rows.map {
                    it.split("").filter { it.isNotEmpty() }.map {
                        when (it) {
                            "." -> 0
                            "#" -> 1
                            else -> throw IllegalArgumentException("unknown character: $it")
                        }
                    }
                }
            )
        }

        fun withDefect(matrix: List<List<Int>>): Field {
            val rows = matrix.size
            val columns = matrix.first().size
            val defectousField = matrix.map { it.toMutableList() }.toMutableList()
            defectousField[0][0] = 1
            defectousField[rows - 1][0] = 1
            defectousField[0][columns - 1] = 1
            defectousField[rows - 1][columns - 1] = 1
            return Field(defectousField)
        }
    }

    fun countLights() = matrix.flatten().sum()

    override fun toString(): String {
        return matrix.map { row ->
            row.map {
                when (it) {
                    0 -> "."
                    1 -> "#"
                    else -> throw IllegalArgumentException()
                }
            }.joinToString("")
        }.joinToString("\n")
    }
}

data class Input(val data: String, val sampleSize: Int)

fun main() {
    val sample = Input(readFile(2015, 18, IO.TYPE.SAMPLE), 5)
    val input = Input(readFile(2015, 18, IO.TYPE.INPUT), 100)
    Day18(input).solve()
}