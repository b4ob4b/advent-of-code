package com.baobab.helpers

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MatrixTest {

    @Test
    fun neighboursOf() {
        val m = List(9) { it + 1 }.chunked(3).toMatrix()
        assertEquals(listOf(2, 4, 5), m.neighboursOf(0, 0, Matrix.NeighbourKind.ALL))
        assertEquals(listOf(2, 4), m.neighboursOf(0, 0, Matrix.NeighbourKind.ADJECENT))
        assertEquals(listOf(5), m.neighboursOf(0, 0, Matrix.NeighbourKind.DIAGONAL))
        assertEquals(listOf(5, 6, 8), m.neighboursOf(2, 2, Matrix.NeighbourKind.ALL))
        assertEquals(listOf(6, 8), m.neighboursOf(2, 2, Matrix.NeighbourKind.ADJECENT))
        assertEquals(listOf(5), m.neighboursOf(2, 2, Matrix.NeighbourKind.DIAGONAL))
        assertEquals(listOf(1, 2, 3, 4, 6, 7, 8, 9), m.neighboursOf(1, 1, Matrix.NeighbourKind.ALL))
        assertEquals(listOf(2, 4, 6, 8), m.neighboursOf(1, 1, Matrix.NeighbourKind.ADJECENT))
        assertEquals(listOf(1, 3, 7, 9), m.neighboursOf(1, 1, Matrix.NeighbourKind.DIAGONAL))
        assertEquals(emptyList(), m.neighboursOf(-2, -2, Matrix.NeighbourKind.DIAGONAL))

        //  do I want to be able to check neighbours from out of bound?
        assertEquals(listOf(2), m.neighboursOf(-1, 0, Matrix.NeighbourKind.DIAGONAL))
        assertEquals(listOf(3, 9), m.neighboursOf(1, 3, Matrix.NeighbourKind.DIAGONAL))
    }

    @Test
    fun positionedNeighboursOf() {
        val m = List(9) { it + 1 }.chunked(3).toMatrix()
        assertEquals(
            listOf(Position(x = 0, y = 1), Position(x = 1, y = 0), Position(x = 1, y = 2), Position(x = 2, y = 1)),
            m.positionedNeighboursOf(Position(1, 1), Matrix.NeighbourKind.ADJECENT)
        )

    }
}