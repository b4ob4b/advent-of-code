package com.baobab.y2018

import utils.AocDay

class Day01 : AocDay(2018, 1) {

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
    Day01().solve()
}
