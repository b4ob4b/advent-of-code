package com.baobab.y2021

import com.baobab.helpers.Position
import com.baobab.helpers.splitLines
import utils.AocDay
import kotlin.math.abs

class Day05 : AocDay(2021, 5, "Hydrothermal Venture") {

    private val lines = input.splitLines().getLines()

    override fun part1(): Int {
        val points = lines
            .filter { it.isVertical() || it.isHorizontal() }
            .mapNotNull {
                it.getConnectionLine()
            }.flatten()

        return points.countOverlappingPoints()
    }

    override fun part2(): Int {
        val points = lines
            .mapNotNull {
                it.getConnectionLine()
            }.flatten()

        return points.countOverlappingPoints()
    }

    data class Line(val start: Position, val end: Position) {

        private val xx = getX()
        private val yy = getY()

        fun isHorizontal() = start.y == end.y
        fun isVertical() = start.x == end.x
        private fun isDiagonal(): Boolean {
            return abs(start.x - end.x) == abs(start.y - end.y)
                    || start.x + end.x == start.y + end.y
        }

        fun getConnectionLine(): List<Position>? {
            if (isHorizontal()) return (xx).map { x -> Position(x, start.y) }
            if (isVertical()) return (yy).map { y -> Position(start.x, y) }
            if (isDiagonal()) return xx.zip(yy).map { (x, y) -> Position(x, y) }
            return null
        }

        private fun getX(): List<Int> {
            if (start.x > end.x) return (start.x downTo end.x).toList()
            return (start.x..end.x).toList()
        }

        private fun getY(): List<Int> {
            if (start.y > end.y) return (start.y downTo end.y).toList()
            return (start.y..end.y).toList()
        }
    }

    private fun List<String>.getLines(): List<Line> {
        return map { l -> l.split(" -> |,".toRegex()).map { it.toInt() } }
            .map { p -> Line(Position(p[0], p[1]), Position(p[2], p[3])) }
    }

    private fun List<Position>.countOverlappingPoints() = groupingBy { it }
        .eachCount()
        .count { it.value > 1 }
}


fun main() {
    Day05().solve()
}
