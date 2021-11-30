package com.baobab.y2018

import utils.Day
import utils.IO

class Day02(input: String) : Day() {

    private val words = input.split("\n")

    override fun part1(): Int {
        return words
            .flatMap { word ->
                word.split("")
                    .filter { it.isNotEmpty() }
                    .groupingBy { it }
                    .eachCount()
                    .map { it.value }
                    .filter { it > 1 }
                    .toSet()
            }
            .groupingBy { it }
            .eachCount()
            .map { it.value }
            .reduce { acc, i -> acc * i }
    }


    override fun part2(): String {
        var bestMatch = ""
        for ((i1, word1) in words.withIndex()) {
            for ((i2, word2) in words.withIndex()) {
                if (i1 == i2) continue
                val matchingLetters = word1.toCharArray().zip(word2.toCharArray()).filter {
                    it.first == it.second
                }.map { it.first }.joinToString("")
                if (matchingLetters.length > bestMatch.length) {
                    bestMatch = matchingLetters
                }
            }
        }

        return bestMatch
    }
}

fun main() {
    val sample = IO.readFile(2018, 2, IO.TYPE.SAMPLE)
    val input = IO.readFile(2018, 2, IO.TYPE.INPUT)

    Day02(input).solve()
}
