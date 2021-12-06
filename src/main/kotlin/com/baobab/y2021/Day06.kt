package com.baobab.y2021

import utils.Day
import utils.IO

class Day06(val input: String) : Day() {

    val data = input.split(",")
        .map { it.toInt() }
        .groupingBy { it }
        .eachCount()
        .entries.associate {
            it.key.toLong() to it.value.toLong()
        }

    override fun part1(): Long {
        return simulate().first().values.sum()

    }

    override fun part2(): Long {
        return simulate().last().values.sum()
    }

    private fun simulate() = sequence {
        val lastGen = (1..256).fold(data) { generation, cycle ->
            if (cycle == (80 + 1)) {
                yield(generation)
            }
            var counter = 0L
            val nextGeneration = mutableMapOf<Long, Long>()
            generation.entries.forEach { (lifeTime, count) ->
                val newLifeTime: Long
                if (lifeTime - 1 < 0) {
                    counter += count
                    newLifeTime = 6
                } else {
                    newLifeTime = lifeTime - 1
                }
                if (nextGeneration.containsKey(newLifeTime)) {
                    nextGeneration[newLifeTime] = nextGeneration[newLifeTime]!! + count
                } else {
                    nextGeneration[newLifeTime] = count
                }
            }

            if (counter > 0) {
                nextGeneration[8] = counter
            }
            nextGeneration
        }
        yield(lastGen)
    }

}

fun main() {
    val sample = IO.readFile(2021, 6, IO.TYPE.SAMPLE)
    val input = IO.readFile(2021, 6, IO.TYPE.INPUT)
    Day06(input).solve()
}
