package com.baobab.y2015

import utils.Day
import utils.IO

class Day12(val data: String) : Day() {

    override fun part1(): Int {
        return data.split("[,:;{}\\]\\[]".toRegex())
            .map {
                try {
                    it.toInt()
                } catch (e: Exception) {
                    null
                }
            }
            .filterNotNull()
            .sum()
    }

    override fun part2(): Int {
        TODO()
    }


}

fun main() {
    val input = IO.readFile(2015, 12)
    Day12(input).solve()
}