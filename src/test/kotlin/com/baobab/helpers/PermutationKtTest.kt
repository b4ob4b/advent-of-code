package com.baobab.helpers

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PermutationKtTest {
    @Test
    fun permute() {
        assertEquals(
            emptySequence<List<Int>>(),
            listOf<Int>().permutations()
        )

        assertEquals(
            listOf(listOf(1)),
            listOf(1).permutations().toList()
        )

        assertEquals(
            setOf(listOf(1, 2), listOf(2, 1)),
            listOf(1, 2).permutations().toSet()
        )


        listOf(1, 2, 3).permutations().toList() shouldContainExactlyInAnyOrder listOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1),
        )
    }
}