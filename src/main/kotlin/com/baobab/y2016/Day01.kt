package com.baobab.y2016

import utils.Day
import utils.IO
import java.lang.Math.abs

enum class Direction(val i: Int) {
    NORTH(0), EAST(1), SOUTH(2), WEST(3)
}

enum class Turn(val i: Int) {
    R(1), L(-1)
}

data class Position(val x: Int, val y: Int) {
    fun getManhattenDistance() = abs(x) + abs(y)
}

data class Instruction(val turn: Turn, val length: Int)

class Santa() {
    private var face: Direction = Direction.NORTH
    private var position: Position = Position(0, 0)
    private var path: List<Position> = mutableListOf()
    private val crossing: HashSet<Position> = hashSetOf()

    fun doInstruction(instruction: Instruction): Santa {
        doTurn(instruction.turn)
        doMovement(instruction.length)
        return this
    }

    fun getManhattenDistance() = position.getManhattenDistance()

    fun getDistanceFirstCrossing() = crossing.first().getManhattenDistance()

    private fun doTurn(turn: Turn) {
        face = Direction.values()[(face.i + turn.i + 4) % 4]
    }

    private fun doMovement(length: Int) {
        (1..length).forEach {
            position = when (face) {
                Direction.NORTH -> Position(position.x, position.y + 1)
                Direction.EAST -> Position(position.x + 1, position.y)
                Direction.SOUTH -> Position(position.x, position.y - 1)
                Direction.WEST -> Position(position.x - 1, position.y)
            }
            checkForCrossing()
        }
    }

    private fun checkForCrossing() {
        if (!path.contains(position)) {
            path = path.plus(position)
        } else {
            crossing += position
        }
    }

}

class Day01(val input: List<String>) : Day() {

    private fun getEndPosition() = input
        .map {
            Instruction(
                Turn.valueOf("${it.subSequence(0, 1)[0]}"), "${it.subSequence(1, it.length)}".toInt()
            )
        }
        .fold(Santa()) { santa: Santa, instruction: Instruction -> santa.doInstruction(instruction) }

    override fun part1(): Any = getEndPosition()
        .getManhattenDistance()

    override fun part2(): Any = TODO()

}

fun main() {
    val data = IO.readFile(2016, 1).split(", ")
    Day01(data).solve()
}