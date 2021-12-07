package utils

import kotlin.system.measureTimeMillis

abstract class AocDay(
    year: Int,
    day: Int,
    name: String = "",
    inputType: IO.TYPE = IO.TYPE.INPUT
) {

    private val displayName = "Year $year - Day ${day.format(2, '0')}: $name"
    val input = IO.readFile(year, day, inputType)

    fun solve() {
        solveWithPerformanceMeasurement()
    }

    abstract fun part1(): Any?

    abstract fun part2(): Any?

    private fun solveWithPerformanceMeasurement() {
        val solutionPart1: Any?
        val solutionPart2: Any?
        val timePart1 = measureTimeMillis { solutionPart1 = part1() }
        val timePart2 = measureTimeMillis { solutionPart2 = part2() }


        println("------ $displayName -------")
        println(" Part | ${"Solution".format(17)} | ${"Time (ms)".format(11)}")
        println("    1 | ${solutionPart1.format(17)} | ${timePart1.format(11)}")
        println("    2 | ${solutionPart2.format(17)} | ${timePart2.format(11)}")
        println()
    }

    private fun Any?.format(padding: Int, char: Char = ' '): String {
        return this.toString().padStart(padding, char)
    }
}