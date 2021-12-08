package com.baobab.sandbox

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.allIndicesOf

internal class RecursionKtTest {

    @Test
    fun allIndicesOf() {
        assertEquals(listOf(0, 4), listOf(1, 2, 3, 4, 1).allIndicesOf(1))
        assertEquals(listOf<Int>(), listOf(1, 2, 3, 4, 1).allIndicesOf(0))
        assertEquals(listOf(0, 1, 2, 3, 4), listOf(1, 1, 1, 1, 1).allIndicesOf(1))
    }
}