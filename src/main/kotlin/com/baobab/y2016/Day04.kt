package com.baobab.y2016

import utils.Day
import utils.IO

class Day04(input: Input) : Day() {

    val codes = input.input.split("\n").map { Code.of(it) }
    val pattern = input.pattern.toRegex()

    override fun part1(): Int {
        return codes
            .filter { it.isReal() }
            .sumOf { it.sector }
    }

    override fun part2(): Int {
        return codes
            .filter { code ->
                val decoded = code.name.map { it.rotate(code.sector) }.joinToString("")
                decoded.contains(pattern)
            }
            .map { it.sector }
            .first()
    }

    private fun Char.rotate(times: Int): Char {
        var newChar = this
        repeat(times) {
            newChar = newChar.rotate()
        }
        return newChar
    }

    private fun Char.rotate(): Char {
        val charOfa = 97
        val charOfz = 122
        if (this.code == charOfz) return Char(charOfa)
        return Char(this.code + 1)
    }

    data class Code(
        val name: CharSequence,
        val sector: Int,
        val checksum: CharSequence
    ) {
        companion object {
            fun of(string: String): Code {
                val digits = "\\d+".toRegex().find(string) ?: throw Exception("invalid string: $string")
                val name = string.subSequence(0, digits.range.first - 1)
                val sector = string.subSequence(digits.range.first, digits.range.last + 1).toString().toInt()
                val checksum = string.subSequence(digits.range.last + 2, string.length - 1)
                return Code(name, sector, checksum)
            }
        }

        fun isReal(): Boolean {
            if (!checksum.all { it in name }) return false

            val positives = name.filter {
                it in checksum
            }.groupingBy { it }.eachCount()

            if (positives.isEmpty()) return false

            val negatives = name.filter { it != '-' }
                .filter {
                    it !in checksum
                }.groupingBy { it }
                .eachCount()

            if (negatives.isNotEmpty()) {
                return positives.minOf { it.value } >=
                        negatives
                            .maxOf { it.value }
            }

            return true
        }
    }

    data class Input(val input: String, val pattern: String)
}

fun main() {
    val sample = Day04.Input(IO.readFile(2016, 4, IO.TYPE.SAMPLE), "very.encrypted.name")
    val input = Day04.Input(
        IO.readFile(2016, 4, IO.TYPE.INPUT), "northpole"
    )

    Day04(input).solve()
}
