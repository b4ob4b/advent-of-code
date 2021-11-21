package com.baobab.y2015

import utils.Day
import utils.IO.readFile

class Day16(input: String) : Day() {

    private val sue = Sue(
        0,
        mapOf(
            "children" to 3,
            "cats" to 7,
            "samoyeds" to 2,
            "pomeranians" to 3,
            "akitas" to 0,
            "vizslas" to 0,
            "goldfish" to 5,
            "trees" to 3,
            "cars" to 2,
            "perfumes" to 1,
        )
    )

    private val sues = input
        .split("\n")
        .map { Sue.fromString(it) }

    override fun part1(): Int {
        return sues
            .first {
                it.things.keys
                    .all { key ->
                        it.things[key] == sue.things[key]
                    }
            }.id
    }

    override fun part2(): Int {
        return sues
            .first {
                it.things.keys
                    .all { key ->
                        when (key) {
                            in listOf("cats", "trees") -> it.things[key]!! > sue.things[key]!!
                            in listOf("pomeranians", "goldfish") -> it.things[key]!! < sue.things[key]!!
                            else -> it.things[key] == sue.things[key]
                        }
                    }
            }.id
    }
}

data class Sue(
    val id: Int,
    val things: Map<String, Int>
) {
    companion object {
        fun fromString(string: String): Sue {
            val parts = string
                .replace(":", "")
                .replace(",", "")
                .split(" ")
            assert(parts.size <= 8)
            return Sue(
                parts[1].toInt(),
                mapOf(
                    parts[2] to parts[3].toInt(),
                    parts[4] to parts[5].toInt(),
                    parts[6] to parts[7].toInt(),
                )
            )
        }
    }
}

fun main() {
    val input = readFile(2015, 16)
    Day16(input).solve()
}