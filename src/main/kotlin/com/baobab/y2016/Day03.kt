package com.baobab.y2016

import utils.Day
import utils.IO

class Day03(private val input: String) : Day() {

    override fun part1(): Int {
        return input.split("\n").map { Triangle.of(it) }.count { it.isValid() }
    }

    override fun part2(): Int {
        val lines = input.split("\n")

        return listOf(
            lines.map { it.subSequence(0, 6) },
            lines.map { it.subSequence(6, 11) },
            lines.map { it.subSequence(11, it.length) }
        ).flatten()
            .map { it.trim().toString().toInt() }
            .chunked(3)
            .map { Triangle(it) }
            .count(Triangle::isValid)
    }

    private data class Triangle(val sides: List<Int>) {
        companion object {
            fun of(string: String): Triangle {
                val sides = string.split(" ")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
                return Triangle(sides)
            }
        }

        fun isValid(): Boolean {
            val ordered = sides.sorted()
            val shorts = ordered.dropLast(1)
            val long = ordered.last()
            return shorts.sum() > long
        }
    }
}


fun main() {
    val input = IO.readFile(2016, 3)
    Day03(input).solve()
}