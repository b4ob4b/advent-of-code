package com.baobab.y2021

import com.baobab.helpers.extractInts
import utils.AocDay

class Day06 : AocDay(2021, 6, "Lanternfish") {

    val data = input.extractInts()
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
            val nextGeneration = (-1L..8L).associateWith { 0L }.toMutableMap()
            generation.entries.forEach { (lifeTime, count) ->
                nextGeneration[lifeTime - 1] = nextGeneration[lifeTime - 1]!! + count
            }
            nextGeneration[6] = nextGeneration[6]!! + nextGeneration[-1]!!
            nextGeneration[8] = nextGeneration[8]!! + nextGeneration[-1]!!
            nextGeneration.remove(-1)
            nextGeneration
        }
        yield(lastGen)
    }

}

fun main() {
    Day06().solve()
}
