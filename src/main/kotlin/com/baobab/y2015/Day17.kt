package com.baobab.y2015

import utils.Day
import utils.IO.Companion.readFile

class Day17(input: Pair<List<Int>, Int>) : Day() {
    private val data = input.first
    private val sumOfElements = input.second
    private val duplicates = data.groupingBy { it }.eachCount().filter { it.value == 2 }
    private val combinations = data.findCombinations(sumOfElements).map { it.sorted() }.toSet()

    override fun part1(): Any? {
        return combinations.map {
            var count = 1
            for (key in duplicates.keys) {
                if (it.count { it == key } == 1) {
                    count = count * 2
                }
            }
            count
        }.sum()
    }

    override fun part2(): Any? {
        val minimumCount = combinations.map { it.size }.minOrNull()!!
        return combinations
            .filter { it.size == minimumCount }
            .map {
                var count = 1
                for (key in duplicates.keys) {
                    if (it.count { it == key } == 1) {
                        count = count * 2
                    }
                }
                count
            }.sum()
    }

    private fun List<Int>.findCombinations(sumOfElements: Int): List<List<Int>> {
        if (this.all { it > sumOfElements }) return listOf(listOf())
        val containersToChooseFrom = this.filter { it <= sumOfElements }
        val result = mutableListOf<List<Int>>()
        for ((i, element) in containersToChooseFrom.withIndex()) {
            for (r in containersToChooseFrom.filterIndexed { index, it -> index != i }
                .findCombinations(sumOfElements - element)) {
                val elements = mutableListOf(element)
                elements.addAll(r)
                result.add(elements)
            }
        }
        return result.filter { it.sum() == sumOfElements }
    }


}

fun main() {
    val sample = Pair("20, 15, 10, 5, 5".split(", ").map { it.toInt() }, 25)
    val input = Pair(readFile(2015, 17).split("\n").map { it.toInt() }, 150)
    Day17(input).solve()
}