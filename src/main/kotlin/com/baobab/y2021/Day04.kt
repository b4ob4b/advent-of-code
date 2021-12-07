package com.baobab.y2021

import com.baobab.helpers.Matrix
import com.baobab.helpers.splitLines
import com.baobab.helpers.toMatrix
import utils.AocDay

class Day04 : AocDay(2021, 4, "Giant Squid") {

    private val numbers = Bingo.getNumbersFromInput(input)
    private val boards = Bingo.createBoardsFromInput(input)
    private val bingo = Bingo(boards, numbers)

    init {
        bingo.play()
    }

    override fun part1(): Int {
        val firstWinner = bingo.boardScores.toList().first()
        val winningBoard = firstWinner.first
        val winningNumber = firstWinner.second
        val sumOfUnmarkedNumbers = bingo.sumOfUnmarkedNumbers(winningBoard, winningNumber)
        return winningNumber * sumOfUnmarkedNumbers
    }

    override fun part2(): Int {
        val lastWinner = bingo.boardScores.toList().last()
        val lastWinningBoard = lastWinner.first
        val lastWinningNumber = lastWinner.second
        val sumOfUnmarkedNumbers = bingo.sumOfUnmarkedNumbers(lastWinningBoard, lastWinningNumber)
        return lastWinningNumber * sumOfUnmarkedNumbers
    }

    private class Bingo(val boards: List<Matrix<Int>>, val numbers: List<Int>) {
        var boardScores = mutableMapOf<Int, Int>()

        fun play() {
            var round = 1
            while (boardScores.size < boards.size) {
                val numbersTaken = numbers.take(round)
                for ((boardNumber, board) in boards.withIndex()) {
                    for (row in board.getRows()) {
                        if (!boardScores.containsKey(boardNumber) && numbersTaken.containsAll(row)) {
                            boardScores.put(boardNumber, numbersTaken.last())
                        }
                    }
                    for (column in board.getCols()) {
                        if (!boardScores.containsKey(boardNumber) && numbersTaken.containsAll(column)) {
                            boardScores.put(boardNumber, numbersTaken.last())
                        }
                    }
                }
                round++
            }
        }

        fun sumOfUnmarkedNumbers(boardNumber: Int, winningNumber: Int): Int {
            val numbersTaken = numbers.take(numbers.indexOf(winningNumber) + 1)
            return boards[boardNumber].matrix.flatten().filter { !numbersTaken.contains(it) }.sum()
        }

        companion object {
            fun createBoardsFromInput(input: String) = input.split("\n\n").drop(1)
                .map {
                    it.splitLines().map {
                        it.split(" ").filter { it.isNotEmpty() }
                    }.toMatrix().convertElementsToInt()
                }

            fun getNumbersFromInput(input: String) = input.splitLines().first().split(",").map { it.toInt() }
        }
    }
}

fun main() {
    Day04().solve()
}
