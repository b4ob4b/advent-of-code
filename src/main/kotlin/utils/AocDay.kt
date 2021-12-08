package utils

import kotlin.system.measureNanoTime

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
        val timePart1 = measureNanoTime { solutionPart1 = part1() }.toDouble() / 1e6
        val timePart2 = measureNanoTime { solutionPart2 = part2() }.toDouble() / 1e6

        prettyPrint(
            Part(1, solutionPart1, timePart1),
            Part(2, solutionPart2, timePart2)
        )
    }

    private fun prettyPrint(part1: Part, part2: Part) {
        val minWidth = 44
        val displayWidth = if (displayName.length + 6 > minWidth) displayName.length + 10 else minWidth
        val hyphens = "=".repeat((displayWidth - displayName.length - 2) / 2)
        val spaces = " ".repeat((displayWidth - minWidth) / 2)
        println("$hyphens $displayName $hyphens")
        printHeader(spaces)
        println("-".repeat(displayWidth))
        printData(spaces, part1)
        printData(spaces, part2)
        println("=".repeat(displayWidth))
        println()
    }

    private fun printHeader(spaces: String) {
        print(spaces)
        print(" Part |")
        print(" ${"Solution".format(20)} |")
        print(" ${"Time (ms)".format(11)} ")
        print(spaces)
        println()
    }

    private fun printData(spaces: String, part: Part) {
        print(spaces)
        print("    ${part.partNumber} |")
        print(" ${part.solution.format(20)} |")
        print(" ${String.format("%.1f", part.time).format(11)} ")
        print(spaces)
        println()
    }

    private fun Any?.format(padding: Int, char: Char = ' '): String {
        return this.toString().padStart(padding, char)
    }

    private data class Part(val partNumber: Int, val solution: Any?, val time: Double)
}