package com.baobab.y2021

import com.baobab.helpers.*
import utils.Day
import utils.IO
import utils.allIndicesOf

class Day03(val input: String) : Day() {

    private val data = input.splitLines()
    private val matrix = data.toMatrix().convertElementsToInt()
    private val rotated = matrix.rotateClockWise().matrix

    override fun part1(): Int {
        return rotated.map { column ->
            val significant = column.groupingBy { it }.eachCount().toList().sortedBy { it.second }.map { it.first }
            Pair(significant.first(), significant.last())
        }.unzip()
            .toList()
            .map { it.joinToString("").toBinary() }
            .product()
    }

    override fun part2(): Int {

        return listOf(1, 2).map { round ->
            var reducingList = data
            for (i in rotated.indices) {
                val column = reducingList.getColumn(i)

                val mostFrequent = if (round == 1) {
                    column.getBitFrequency().mostFrequent
                } else {
                    column.getBitFrequency().leastFrequent
                }

                val indicesToKeep = column.reversed().allIndicesOf(mostFrequent)
                reducingList = reducingList.filterIndexed { index, _ -> indicesToKeep.contains(index) }
                if (reducingList.size == 1) break
            }
            reducingList.joinToString("").toBinary()
        }.product()
    }

    private fun List<String>.getColumn(columnNumber: Int) =
        this.toMatrix().convertElementsToInt().rotateClockWise().matrix[columnNumber]

    private data class BitFrequency(val mostFrequent: Int, val leastFrequent: Int)

    private fun List<Int>.getBitFrequency(): BitFrequency {
        val count = this.groupingBy { it }.eachCount().toList()
        val frequencies = count.map { it.second }
        if (frequencies.toSet().size == 1) {
            return BitFrequency(1, 0)
        }
        val mostFrequent = count.sortedByDescending { it.second }.first().first
        val leastFrequent = mostFrequent.flip()
        return BitFrequency(mostFrequent, leastFrequent)
    }

    private fun Matrix<String>.convertElementsToInt() = this.matrix.map { it.map { it.toInt() } }.toMatrix()

    private fun Int.flip() = if (this == 1) 0 else 1
}

fun main() {
    val sample = IO.readFile(2021, 3, IO.TYPE.SAMPLE)
    val input = IO.readFile(2021, 3, IO.TYPE.INPUT)

    Day03(input).solve()
}