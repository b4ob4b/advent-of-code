package com.baobab.y2021

import com.baobab.helpers.product
import com.baobab.helpers.splitLines
import com.baobab.helpers.toBinary
import com.baobab.helpers.toMatrix
import utils.AocDay
import utils.allIndicesOf

class Day03 : AocDay(2021, 3, "Binary Diagnostic") {

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

    private fun Int.flip() = if (this == 1) 0 else 1
}

fun main() {
    Day03().solve()
}