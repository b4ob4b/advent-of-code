package com.baobab.y2015

import utils.Day
import utils.IO.print
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

class Day20(val input: Int) : Day() {

    private fun bringPresents1(house: Int): Int {
        var presents = 0
        val lastHouse = sqrt(house.toDouble()).toInt() + 1
        for (elf in 1..lastHouse) {
            if (house notVisitedBy elf) continue
            presents += elf
            presents += house / elf
        }
        return presents * 10
    }


    private fun bringPresents2(house: Int): Int {
        var presents = 0
        val lastHouse = sqrt(house.toDouble()).toInt() + 1
        for (elf in 1..lastHouse) {
            if (house notVisitedBy elf) continue
            if (elf <= 50) {
                presents += house / elf
            }
            if (house / elf <= 50) {
                presents += elf
            }

        }
        return presents * 11
    }

    private infix fun Int.notVisitedBy(int: Int): Boolean = this % int != 0

    private fun visitHouses(bringPresents: (Int) -> Int): Int {
        var house = 1
        while (bringPresents(house) < input) {
            house += 1
        }
        return house
    }

    override fun part1() = visitHouses(this::bringPresents1)

    override fun part2() = visitHouses(this::bringPresents2)
}


fun main() {
    val input = 34000000
    measureTimeMillis {
        Day20(input).solve()
    }.print()
}