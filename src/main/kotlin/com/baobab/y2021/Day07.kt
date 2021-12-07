package com.baobab.y2021

import utils.AocDay
import kotlin.math.abs

class Day07 : AocDay(2021, 7, "The Treachery of Whales") {
    private val data = input.split(",").map { it.toInt() }
    private val fuelcost = List(data.maxOrNull()!!) { it + 1 }.runningFold(0) { acc, i -> acc + i }
    private val possibleHorizontals = 1..data.maxOrNull()!!

    override fun part1(): Int {
        return possibleHorizontals.minOf { value ->
            data.sumOf { abs(it - value) }
        }
    }


    override fun part2(): Int {
        return possibleHorizontals.minOf { value ->
            data.sumOf { fuelcost[abs(it - value)] }
        }
    }
}

fun main() {
    Day07().solve()
}
