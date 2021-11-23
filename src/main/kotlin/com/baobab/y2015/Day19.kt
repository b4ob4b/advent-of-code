package com.baobab.y2015

import utils.Day
import utils.IO

class Day19(val input: String) : Day() {
    private val sample = input.split("\n").last()
    val replacements = retrieveReplacements()

    override fun part1(): Int {
        val combinations = mutableSetOf<String>()
        for ((key, value) in replacements) {
            var index = sample.indexOf(key)
            while (index >= 0) {
                var word = sample
                word = word.replaceFirstAfter(key, value, index)
                combinations.add(word)
                index = sample.indexOf(key, index + 1)
            }
        }
        return combinations.size
    }

    private fun retrieveReplacements(): List<Pair<String, String>> {
        val lines = input.split("\n")

        return lines.subList(0, lines.indexOfFirst { it.isEmpty() })
            .map {
                val parts = it.replace(" ", "").split("=>")
                parts[0] to parts[1]
            }
    }

    private fun String.replaceFirstAfter(oldValue: String, newValue: String, index: Int): String {
        val part1 = this.subSequence(0, index).toString()
        val part2 = this.subSequence(index, this.length)
        return part1 + part2.toString().replaceFirst(oldValue, newValue)
    }

    override fun part2(): Int {
        var word = sample
        var changed = true
        var rounds = 0
        while (changed) {
            changed = false
            for ((key, value) in replacements) {
                if (!word.contains(value)) continue

                changed = true
                rounds++
                word = word.replaceFirst(value, key)
            }
        }
        return rounds
    }


}

fun main() {
    val sample = IO.readFile(2015, 19, IO.TYPE.SAMPLE)
    val input = IO.readFile(2015, 19, IO.TYPE.INPUT)

    Day19(input).solve()
}
