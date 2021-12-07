package com.baobab.y2021

import utils.AocDay

class Day01 : AocDay(2021, 1, "Sonar Sweep") {
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
    Day01().solve()
}
