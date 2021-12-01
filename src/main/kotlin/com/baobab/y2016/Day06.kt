package com.baobab.y2016

import utils.Day
import utils.IO

class Day06(input: String) : Day() {

    private val words = input.split("\n")

    private fun findLetters(foo: List<Pair<String, Int>>.(Int) -> List<Pair<String, Int>>): String {
        val positions = MutableList(words.first().length) { "" }
        for (i in 0 until words.first().length) {
            positions[i] += words.map { it[i] }.joinToString("")
        }
        return positions.flatMap { position ->
            position.split("").filter { it.isNotEmpty() }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedByDescending { it.second }
                .foo(1)
                .map { it.first }
        }.joinToString("")
    }

    override fun part1() = findLetters((List<Pair<String, Int>>::take))

    override fun part2() = findLetters((List<Pair<String, Int>>::takeLast))
}


fun main() {
    val sample = IO.readFile(2016, 6, IO.TYPE.SAMPLE)
    val input = IO.readFile(2016, 6, IO.TYPE.INPUT)

    Day06(input).solve()
}
