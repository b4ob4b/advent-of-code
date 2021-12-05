package com.baobab.y2015

import com.baobab.helpers.Cube
import com.baobab.helpers.splitLines
import utils.Day
import utils.IO

class Day02(val input: String) : Day() {

    override fun part1(): Int {
        return input.splitLines().map { it.toCube() }.sumOf { it.getSlack() + it.getSurfaceArea() }
    }

    override fun part2(): Int {
        return input.split("\n").map { it.toCube() }.sumOf { it.getVolume() + it.getFaceArea() }
    }

    private fun String.toCube(): Cube {
        val parts = this.split("x").map { it.toInt() }
        return Cube(parts[0], parts[1], parts[2])
    }

    private fun Cube.getSlack() = this.sides
        .sorted()
        .take(2)
        .reduce { acc, i -> acc * i }
}

fun main() {
    val sample = IO.readFile(2015, 2, IO.TYPE.SAMPLE)
    val input = IO.readFile(2015, 2, IO.TYPE.INPUT)

    Day02(input).solve()
}
