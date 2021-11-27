package com.baobab.y2015

import utils.Day
import utils.IO

open class Day08(val input: String) : Day() {

    val lines = input.split("\n")

    override fun part1(): Any? {
        return lines.map {
            it.length - it.lengthDecoded()
        }.sum()
    }

    override fun part2(): Any? {
        return lines.map {
            it.lengthEncoded() - it.length
        }.sum()
    }

    internal fun String.lengthDecoded(): Int {
        var line = this

        line = line.replace("\\\"", "\"")
        line = line.replace("\\\\", "\\")
        line = line.replace("\\\\x[0-9a-f][0-9a-f]".toRegex(), "1")

        return line.length - 2
    }

    internal fun String.lengthEncoded(): Int {
        var line = this

        line = line.replace("\\", "\\\\")
        line = line.replace("\"", "\\\"")

        return line.length + 2
    }
}

fun main() {
    val sample = IO.readFile(2015, 8, IO.TYPE.SAMPLE)
    val input = IO.readFile(2015, 8, IO.TYPE.INPUT)

    Day08(input).solve()
}