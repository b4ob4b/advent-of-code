package com.baobab.helpers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PermutationKtTest {
    @Test
    fun permute() {
        assertEquals(
            emptySequence<List<Int>>(),
            listOf<Int>().permute()
        )

        assertEquals(
            listOf(listOf(1)),
            listOf(1).permute().toList()
        )

        assertEquals(
            setOf(listOf(1, 2), listOf(2, 1)),
            listOf(1, 2).permute().toSet()
        )

        assertEquals(
            setOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1),
            ),
            listOf(1, 2, 3).permute().toSet()
        )


    }
}