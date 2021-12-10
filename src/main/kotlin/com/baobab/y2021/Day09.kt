package com.baobab.y2021

import com.baobab.helpers.*
import utils.AocDay

fun main() {
    Day09().solve()
}

class Day09 : AocDay("Smoke Basin") {

    private val matrix = input.splitLines().toMatrix().convertElementsToInt()
    private val sinks = getSinks()

    override fun part1(): Int {
        return sinks.sumOf { (row, col) -> matrix.getValue(row, col) + 1 }
    }

    override fun part2(): Int {
        val sinks = getSinks()
        return sinks.map { sink ->
            mutableSetOf(sink).also { basin ->
                basin.addAll(sink.findNeighbours())
            }.count()
        }.sortedByDescending { it }
            .take(3)
            .product()
    }

    private fun getSinks(): List<Position> {
        val sinks = mutableListOf<Position>()
        for (r in matrix.getRows().indices) {
            for (c in matrix.getCols().indices) {
                if (matrix.neighboursOf(r, c, Matrix.NeighbourKind.ADJECENT).all {
                        it > matrix.matrix[r][c]
                    }) {
                    sinks.add(Position(r, c))
                }
            }
        }
        return sinks
    }

    private fun Position.findNeighbours(isFirstVisit: MutableSet<Position> = mutableSetOf()): Set<Position> {
        if (!isFirstVisit.add(this)) return setOf()

        val neighbours = matrix
            .positionedNeighboursOf(this, Matrix.NeighbourKind.ADJECENT)
            .filter { neighbour -> matrix.getValue(neighbour) < 9 && matrix.getValue(neighbour) >= matrix.getValue(this) }
            .toMutableSet()

        if (neighbours.isEmpty()) {
            return setOf()
        }
        val neighboursOfNeighbours = neighbours.flatMap { it.findNeighbours(isFirstVisit) }.toSet()
        neighbours.addAll(neighboursOfNeighbours)
        return neighbours
    }
}
