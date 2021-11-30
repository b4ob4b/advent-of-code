package com.baobab.y2016

import utils.Day
import utils.IO

class Day02(val input: String) : Day() {

    private val instructions = input.split("\n")
        .map {
            it.split("").filter { it.isNotEmpty() }.map { Direction.valueOf(it) }
        }

    enum class KeyPadType {
        NORMAL, STAR;

        fun getKeyPad(): Map<Position, String> {
            return when (this) {
                NORMAL -> mapOf(
                    Position(0, 0) to "1",
                    Position(1, 0) to "2",
                    Position(2, 0) to "3",
                    Position(0, 1) to "4",
                    Position(1, 1) to "5",
                    Position(2, 1) to "6",
                    Position(0, 2) to "7",
                    Position(1, 2) to "8",
                    Position(2, 2) to "9"
                )
                STAR -> mapOf(
                    Position(2, 0) to "1",
                    Position(1, 1) to "2",
                    Position(2, 1) to "3",
                    Position(3, 1) to "4",
                    Position(0, 2) to "5",
                    Position(1, 2) to "6",
                    Position(2, 2) to "7",
                    Position(3, 2) to "8",
                    Position(4, 2) to "9",
                    Position(1, 3) to "A",
                    Position(2, 3) to "B",
                    Position(3, 3) to "C",
                    Position(2, 4) to "D",
                )
            }
        }
    }

    class KeyPad(keyPadType: KeyPadType, startPosition: Position) {

        private val keyPad = keyPadType.getKeyPad()
        private var currentFingerPosition = startPosition

        fun doFingerMovement(direction: Direction): KeyPad {
            if (isMovementAllowed(direction)) goDirection(direction)
            return this
        }

        fun pressKey() = keyPad[currentFingerPosition]

        private fun isMovementAllowed(direction: Direction): Boolean {
            return currentFingerPosition.doMovement(direction) in keyPad.keys
        }

        private fun goDirection(direction: Direction) {
            currentFingerPosition = currentFingerPosition.doMovement(direction)
        }
    }

    data class Position(var x: Int, var y: Int) {
        fun doMovement(direction: Direction): Position {
            return when (direction) {
                Direction.U -> Position(x, y - 1)
                Direction.D -> Position(x, y + 1)
                Direction.R -> Position(x + 1, y)
                Direction.L -> Position(x - 1, y)
            }
        }
    }

    enum class Direction { U, L, R, D }

    override fun part1(): Int {
        val keyPad = KeyPad(KeyPadType.NORMAL, startPosition = Position(1, 1))
        val numbers = doInstructionsOn(keyPad)
        return numbers.joinToString("").toInt()
    }

    override fun part2(): String {
        val keyPad = KeyPad(KeyPadType.STAR, startPosition = Position(0, 2))
        val numbers = doInstructionsOn(keyPad)
        return numbers.joinToString("")
    }

    private fun doInstructionsOn(keyPad: KeyPad): List<String?> {
        val numbers = instructions.map { instruction ->
            instruction.fold(keyPad) { keyPad, direction ->
                keyPad.doFingerMovement(direction)
            }.pressKey()
        }
        return numbers
    }
}

fun main() {
    val sample = IO.readFile(2016, 2, IO.TYPE.SAMPLE)
    val input = IO.readFile(2016, 2, IO.TYPE.INPUT)

    Day02(input).solve()
}
