package utils

@Deprecated("Day is deprecated", ReplaceWith("AocDay()"))
abstract class Day {

    fun solve() {
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
    }

    abstract fun part1(): Any?

    abstract fun part2(): Any?
}
