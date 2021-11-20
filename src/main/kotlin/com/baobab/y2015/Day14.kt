package com.baobab.y2015

import utils.Day
import utils.allIndicesOf

class Day14(val input: Pair<String, Int>) : Day() {

    private val reindeers = input.first.split("\n").map { it.trimIndent() }.map { Reindeer.fromInput(it) }


    override fun part2(): Any? {
        for (second in 1..input.second) {
            val distances = reindeers.map { it.positionAfter(second) }
            val scoreUps = distances.allIndicesOf(distances.maxOrNull()!!)
            for (scoreUp in scoreUps) {
                reindeers.get(scoreUp).score++
            }
        }

        return reindeers.map { it.score }.maxOrNull()
    }

    override fun part1(): Any? {
        return reindeers.map { it.positionAfter(input.second) }.maxOrNull()
    }
}

data class Reindeer(
    val name: String,
    val speed: Int,
    val speedyTime: Int,
    val rest: Int,
    var score: Int = 0,
) {
    companion object {
        fun fromInput(string: String): Reindeer {
            val words = string.split(" ")
            return Reindeer(words[0], words[3].toInt(), words[6].toInt(), words[13].toInt())
        }
    }

    fun positionAfter(seconds: Int): Int {
        val cycleTime = speedyTime + rest
        val cycles = seconds.div(cycleTime)
        val restOfOverallTime = seconds - cycles * cycleTime
        val additionalSpeedyTime = if (restOfOverallTime > speedyTime) speedyTime else restOfOverallTime
        val distance = cycles * speedyTime * speed + additionalSpeedyTime * speed
        return distance
    }
}

fun main() {
    val sample = Pair(
        """
        Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
        Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds
    """.trimIndent(), 1000
    )

    val input = Pair(
        """
        Dancer can fly 27 km/s for 5 seconds, but then must rest for 132 seconds.
        Cupid can fly 22 km/s for 2 seconds, but then must rest for 41 seconds.
        Rudolph can fly 11 km/s for 5 seconds, but then must rest for 48 seconds.
        Donner can fly 28 km/s for 5 seconds, but then must rest for 134 seconds.
        Dasher can fly 4 km/s for 16 seconds, but then must rest for 55 seconds.
        Blitzen can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
        Prancer can fly 3 km/s for 21 seconds, but then must rest for 40 seconds.
        Comet can fly 18 km/s for 6 seconds, but then must rest for 103 seconds.
        Vixen can fly 18 km/s for 5 seconds, but then must rest for 84 seconds.
    """.trimIndent(), 2503
    )
    Day14(input).solve()
}