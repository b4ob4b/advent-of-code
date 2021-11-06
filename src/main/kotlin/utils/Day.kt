package utils

abstract class Day {

    fun solve() {
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
    }

    abstract fun part2(): Any?

    abstract fun part1(): Any?
}
