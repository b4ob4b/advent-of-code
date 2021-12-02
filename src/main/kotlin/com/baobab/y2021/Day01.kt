package com.baobab.y2021

import utils.Day
import utils.IO

class Day01(val input: String) : Day() {
    private val measurements = input.split("\n")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

    override fun part1(): Int {
        return measurements
            .countIncrements()
    }

    override fun part2(): Int {
        return measurements
            .windowed(3) { it.sum() }
            .countIncrements()
    }

    private fun Collection<Int>.countIncrements() =
        asSequence()
            .zipWithNext { a, b -> b - a }
            .count { it > 0 }
}

fun main() {
    val sample = IO.readFile(2021, 1, IO.TYPE.SAMPLE)
    val input = IO.readFile(2021, 1, IO.TYPE.INPUT)

    Day01(input).solve()
}
