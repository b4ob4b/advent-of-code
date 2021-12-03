package com.baobab.helpers

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {
    fun getManhattenDistance() = abs(x) + abs(y)

    fun doMovement(direction: Direction): Position {
        return when (direction) {
            Direction.U -> Position(x, y - 1)
            Direction.D -> Position(x, y + 1)
            Direction.R -> Position(x + 1, y)
            Direction.L -> Position(x - 1, y)
        }
    }
}
