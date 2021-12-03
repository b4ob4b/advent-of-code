package com.baobab.y2021

import utils.Day
import utils.IO

class Day02(input: String) : Day() {

    private val instructions = input.split("\n")
        .map { it.split(" ") }
        .map { Instruction(Direction.valueOf(it.first().uppercase()), it.last().toInt()) }

    private data class Submarine(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)
    private enum class Direction { FORWARD, DOWN, UP }
    private data class Instruction(val direction: Direction, val value: Int)

    private fun Submarine.move(instruction: Instruction): Submarine {
        return when (instruction.direction) {
            Direction.FORWARD -> Submarine(horizontal + instruction.value, depth, 0)
            Direction.DOWN -> Submarine(horizontal, depth + instruction.value, 0)
            Direction.UP -> Submarine(horizontal, depth - instruction.value, 0)
        }
    }

    private fun Submarine.product(): Int {
        return horizontal * depth
    }

    override fun part1() = instructions.fold(Submarine()) { submarine, instruction ->
        submarine.move(instruction)
    }.product()


    private fun Submarine.moveWithAim(instruction: Instruction): Submarine {
        return when (instruction.direction) {
            Direction.FORWARD -> Submarine(horizontal + instruction.value, depth + aim * instruction.value, aim)
            Direction.DOWN -> Submarine(horizontal, depth, aim + instruction.value)
            Direction.UP -> Submarine(horizontal, depth, aim - instruction.value)
        }
    }

    override fun part2() = instructions.fold(Submarine()) { submarine, instruction ->
        submarine.moveWithAim(instruction)
    }.product()
}

fun main() {
    val sample = IO.readFile(2021, 2, IO.TYPE.SAMPLE)
    val input = IO.readFile(2021, 2, IO.TYPE.INPUT)

    Day02(input).solve()
}
