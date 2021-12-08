package com.baobab.y2021

import com.baobab.helpers.permute
import com.baobab.helpers.splitLines
import utils.AocDay

class Day08 : AocDay(2021, 8, "Seven Segment Search") {

    private val lines = input.splitLines().map { it.split("|") }
    private val eight = "acedgfb".toList().sorted()
    private val segments = mapOf(
        "acedgfb" to 8,
        "cdfbe" to 5,
        "gcdfa" to 2,
        "fbcad" to 3,
        "dab" to 7,
        "cefabd" to 9,
        "cdfgeb" to 6,
        "eafb" to 4,
        "cagedb" to 0,
        "ab" to 1,
    ).mapKeys { it.key.toList().sorted() }

    private val permutations: List<List<Char>> = eight.permute().toList().map { it.toList() }

    override fun part1(): Int {
        return lines.flatMap { line ->
            val words = line.getUnprocessed()
            words.mapNotNull {
                when (it.length) {
                    2 -> 1
                    4 -> 4
                    3 -> 7
                    7 -> 8
                    else -> null
                }
            }
        }.count()
    }

    override fun part2(): Int {
        var translationMap: Map<Char, Char>? = null
        return lines.sumOf { line ->
            val incoming = line.getIncoming()
            val unprocessed = line.getUnprocessed()

            permutations.takeWhile { permutation ->
                var searching = true
                if (incoming.all { word ->
                        translationMap = permutation.zip(eight).toMap()
                        val digit = segments[word.translate(translationMap)]
                        digit != null
                    }) {
                    searching = false
                }
                searching
            }

            unprocessed.map { word ->
                segments[word.translate(translationMap)]
            }.joinToString("").toInt()

        }
    }

    private fun String.translate(translationMap: Map<Char, Char>?) =
        this.toList().sorted().map { translationMap!![it]!! }.toList().sorted()

    private fun List<String>.getIncoming() = this.first().split(" ").filter { it.isNotEmpty() }
    private fun List<String>.getUnprocessed() = this[1].split(" ").filter { it.isNotEmpty() }
}

fun main() {
    Day08().solve()
}
