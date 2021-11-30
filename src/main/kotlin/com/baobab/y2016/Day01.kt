package com.baobab.y2016

import utils.Day
import utils.IO
import java.lang.Math.abs

class Day01(val input: String) : Day() {

    private fun moveToEndPosition() = input.split(", ")
        .map {
            Instruction(
                Turn.valueOf("${it.subSequence(0, 1)[0]}"), "${it.subSequence(1, it.length)}".toInt()
            )
        }
        .fold(Santa()) { santa: Santa, instruction: Instruction -> santa.doInstruction(instruction) }

    override fun part1(): Int = moveToEndPosition().getManhattenDistance()

    override fun part2(): Int = moveToEndPosition().getFirstCrossing().getManhattenDistance()

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
        private val crossing: MutableList<Position> = mutableListOf()

        fun doInstruction(instruction: Instruction): Santa {
            doTurn(instruction.turn)
            doMovement(instruction.length)
            return this
        }

        fun getManhattenDistance() = position.getManhattenDistance()

        fun getFirstCrossing() = crossing.first()

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
            if (path.contains(position)) {
                crossing += position
            }
            path = path.plus(position)
        }
    }

}

fun main() {
    val sample = IO.readFile(2016, 1, IO.TYPE.SAMPLE)
    val input = IO.readFile(2016, 1)
    Day01(input).solve()
}