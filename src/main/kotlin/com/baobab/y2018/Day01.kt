package com.baobab.y2018

import utils.Day
import utils.IO

class Day01(input: String) : Day() {

    val data = input.split("\n")
        .map { it.toInt() }

    override fun part1() = data.sum()

    override fun part2(): Int {
        val frequencies = mutableSetOf(0)

        while (true) {
            var frequency = frequencies.last()
            for (f in data) {
                frequency += f
                if (!frequencies.add(frequency)) {
                    return frequency
                }
            }
        }
    }
}

fun main() {
    val sample = IO.readFile(2018, 1, IO.TYPE.SAMPLE)
    val input = IO.readFile(2018, 1, IO.TYPE.INPUT)

    Day01(input).solve()
}
