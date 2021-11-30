package com.baobab.y2018

import utils.Day
import utils.IO

class Day03(input: String) : Day() {

    private val claims = input
        .split("\n")
        .map { it.toClaim() }

    override fun part1(): Int {
        return claims
            .flatMap { it.getPositions() }
            .groupingBy { it }
            .eachCount()
            .count { it.value >= 2 }
    }

    override fun part2(): Int {
        val excludedClaims = mutableSetOf<Claim>()
        outer@ for (claim in claims) {
            if (excludedClaims.contains(claim)) continue
            for (other in claims) {
                if (claim == other) continue
                if (claim.getPositions().intersect(other.getPositions()).isNotEmpty()) {
                    excludedClaims.addAll(setOf(claim, other))
                    continue@outer
                }
            }
            return claim.id
        }
        throw Exception("no Intersection")
    }

    private fun String.toClaim(): Claim {
        val parts = this.split(" ")
        return Claim(
            parts[0].replace("#", "").toInt(),
            parts[2].toPosition(),
            parts[3].toSize()
        )
    }

    private fun String.toPosition(): Position {
        val parts = this.replace(":", "")
            .split(",")
            .map { it.toInt() }
        return Position(parts[0], parts[1])
    }

    private fun String.toSize(): Size {
        val parts = this
            .split("x")
            .map { it.toInt() }
        return Size(parts[0], parts[1])
    }

    private data class Claim(val id: Int, val position: Position, val size: Size) {
        fun getPositions(): Set<Position> {
            val positions = mutableListOf<Position>()
            for (x in position.x until (position.x + size.x)) {
                for (y in position.y until (position.y + size.y)) {
                    positions.add(Position(x, y))
                }
            }
            return positions.toSet()
        }
    }

    private data class Position(val x: Int, val y: Int)
    private data class Size(val x: Int, val y: Int)
}

fun main() {
    val sample = IO.readFile(2018, 3, IO.TYPE.SAMPLE)
    val input = IO.readFile(2018, 3, IO.TYPE.INPUT)

    Day03(input).solve()
}
