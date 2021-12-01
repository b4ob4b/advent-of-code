package com.baobab.y2021

import utils.Day
import utils.IO

class Day01(val input: String) : Day() {
    private val measurements = input.split("\n")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

    override fun part1(): Int {
        return measurements
            .windowed(2)
            .map { it.last() - it.first() }
            .count { it > 0 }
    }

    override fun part2(): Int {
        return generateSequence(0) { it + 1 }
            .take(measurements.size)
            .windowed(3)
            .map { chunk -> chunk.map { measurements[it] } }
            .map { it.sum() }
            .windowed(2)
            .map { it.last() - it.first() }
            .count { it > 0 }
    }
}

fun main() {
    val sample = IO.readFile(2021, 1, IO.TYPE.SAMPLE)
    val input = IO.readFile(2021, 1, IO.TYPE.INPUT)

    Day01(input).solve()
}
