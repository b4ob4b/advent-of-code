package com.baobab.y2021

import com.baobab.helpers.Matrix
import com.baobab.helpers.splitLines
import com.baobab.helpers.toMatrix
import utils.Day
import utils.IO

class Day04(input: String) : Day() {

    private val numbers = input.splitLines().first().split(",").map { it.toInt() }

    private val fields = input.split("\n\n").drop(1)
        .map {
            it.splitLines().map {
                it.split(" ").filter { it.isNotEmpty() }
            }
        }

    private val matrices = fields.map { it.toMatrix().convertElementsToInt() }

    private fun Matrix<String>.convertElementsToInt() = this.matrix.map { it.map { it.toInt() } }.toMatrix()

    override fun part1(): Int {
        var numbersTaken = 1
        val winner: Int
        outer@ while (true) {
            val chosen = numbers.take(numbersTaken)
            for ((i, m) in matrices.withIndex()) {
                for (r in m.matrix.indices) {
                    if (chosen.containsAll(m.matrix[r])) {
                        winner = i
                        break@outer
                    }
                }
                for (c in m.matrix.first().indices) {
                    if (chosen.containsAll(m.rotateClockWise().matrix[c])) {
                        winner = i
                        break@outer
                    }
                }
            }
            numbersTaken++
        }
        val score = matrices.calculateScore(winner, numbersTaken)
        val lastNumber = numbers.take(numbersTaken).last()
        return lastNumber * score
    }

    override fun part2(): Int {
        var numbersTaken = 1
        val players = MutableList(matrices.size) { it + 1 }.map { it - 1 }.toMutableList()
        while (players.size > 1) {
            val chosen = numbers.take(numbersTaken)
            for ((i, m) in matrices.withIndex()) {
                for (r in m.matrix.indices) {
                    if (chosen.containsAll(m.matrix[r])) {
                        players.remove(i)
                    }
                }
                for (c in m.matrix.first().indices) {
                    if (chosen.containsAll(m.rotateClockWise().matrix[c])) {
                        players.remove(i)
                    }
                }
            }
            numbersTaken++
        }
        val score = matrices.calculateScore(players.first(), numbersTaken)
        val lastNumber = numbers.take(numbersTaken).last()
        return lastNumber * score
    }

    private fun List<Matrix<Int>>.calculateScore(winnerNumber: Int, gameRound: Int): Int {
        return this[winnerNumber].matrix.flatten().filter {
            !numbers.take(gameRound).contains(it)
        }.sum()
    }
}

fun main() {
    val sample = IO.readFile(2021, 4, IO.TYPE.SAMPLE)
    val input = IO.readFile(2021, 4, IO.TYPE.INPUT)

    Day04(input).solve()
}
