package com.baobab.y2021

import com.baobab.helpers.splitLines
import utils.AocDay
import java.util.*

fun main() {
    Day10().solve()
}

class Day10 : AocDay("Syntax Scoring") {

    private val data = parseBrackets()

    override fun part1(): Int {
        return data.filter { it.containsKey(Syntax.CORRUPT) }
            .flatMap { it.values.map { it.pop().complement() } }
            .map { it.score(Syntax.CORRUPT) }
            .groupingBy { it }
            .eachCount()
            .map { it.key * it.value }
            .sum()
    }

    override fun part2(): Long {
        val incomplete = data.filter { it.containsKey(Syntax.INCOMPLETE) }
            .flatMap {
                it.values.map { stack ->
                    stack.toList().reversed()
                        .map { bracket -> bracket.complement().score(Syntax.INCOMPLETE) }
                        .fold(0L) { acc, i -> 5L * acc + i }
                }
            }.sorted()
        return incomplete[(incomplete.size / 2)]
    }

    enum class Syntax { CORRUPT, INCOMPLETE }

    private fun parseBrackets() = input.splitLines().map { line ->
        val stack = Stack<Char>()
        var syntax = Syntax.INCOMPLETE
        for (word in line) {
            if (listOf('[', '(', '{', '<').contains(word)) {
                stack.push(word)
            }
            if (listOf(']', ')', '}', '>').contains(word)) {
                if (stack.pop().complement() == word) continue
                else {
                    stack.push(word.complement())
                    syntax = Syntax.CORRUPT
                    break
                }
            }
        }
        mapOf(syntax to stack)
    }

    private fun Char.complement() = when (this) {
        '[' -> ']'
        '(' -> ')'
        '{' -> '}'
        '<' -> '>'
        ']' -> '['
        ')' -> '('
        '}' -> '{'
        '>' -> '<'
        else -> this.error()
    }

    private fun Char.score(syntax: Syntax) = when (syntax) {
        Syntax.CORRUPT -> scoreForCorruptedLines()
        Syntax.INCOMPLETE -> scoreForIncompleteLines()
    }

    private fun Char.scoreForCorruptedLines() = when (this) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> this.error()
    }

    private fun Char.scoreForIncompleteLines() = when (this) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> this.error()
    }

    private fun Char.error(): Nothing = throw Exception("unknown character $this")
}           