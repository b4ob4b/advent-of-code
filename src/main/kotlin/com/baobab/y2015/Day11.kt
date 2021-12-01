package com.baobab.y2015

import utils.Day

class Day11(input: String) : Day() {

    private var password = input.toCharArray().map { it.code }
    private val forbiddenLetters = listOf('i', 'o', 'l').map { it.code }

    override fun part1(): String {
        return createNextPassword()
    }

    override fun part2(): String {
        password.increment()
        return createNextPassword()
    }

    private fun createNextPassword(): String {
        while (
            !(password.containsTwoDifferentPairs() &&
                    password.hasThreeInARow() &&
                    password.onlyUsesAllowedLetters())
        ) {
            password.increment()
        }
        return password.map { it.toChar() }.joinToString("")
    }

    private fun List<Int>.increment() {
        val mPassword = this.toMutableList()
        var place = password.size - 1
        while (++mPassword[place] > 122) {
            mPassword[place] = 97
            place--
        }
        password = mPassword
    }

    private fun List<Int>.onlyUsesAllowedLetters(): Boolean {
        return this.none { it in forbiddenLetters }
    }

    private fun List<Int>.containsTwoDifferentPairs(): Boolean {
        val pairs = this.windowed(2).filter { it.first() == it.last() }.flatten()
        if (pairs.isEmpty()) return false
        val firstPair = pairs.first()
        val secondPair = pairs.last()
        return secondPair != firstPair
    }

    private fun List<Int>.hasThreeInARow(): Boolean {
        return this.windowed(3)
            .any {
                it.windowed(2).all { number ->
                    number.last() - number.first() == 1
                }
            }
    }
}

fun main() {
    val sample = "abcdefgh"
    val input = "hxbxwxba"

    Day11(input).solve()
}
