package com.baobab.y2015

import utils.Day
import utils.Utils
import java.util.regex.Pattern

class Day12(val data: String): Day() {
    override fun part2(): Any? {
//        TODO("Not yet implemented")
        return ""
    }

    override fun part1(): Any? {
        return data.split("[,:;{}\\]\\[]".toRegex())
            .map {
            try {
                it.toInt()
            } catch (e: Exception) {
                null
            }
        }
            .filterNotNull()
            .sum()
    }
}

fun main() {
    val input = Utils.readFile2(2015,12)
    Day12(input).solve()
//    Day12("[1,2,3]").solve()
//    Day12("{\"a\":2,\"b\":4}").solve()
//    Day12("{\"a\":{\"b\":4},\"c\":-12;}").solve()
//    println(input)
}