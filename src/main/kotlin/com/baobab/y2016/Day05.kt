package com.baobab.y2016

import utils.Day
import java.math.BigInteger
import java.security.MessageDigest

class Day05(input: String) : Day() {
    
    val decrypter = Decrypter(input)

    override fun part1(): String {
        var password = ""
        while (password.length < 8) {
            password += decrypter.next().first
        }

        return password
    }

    override fun part2(): String {

        decrypter.reset()
        val password = "________".toCharArray()

        while (password.any { it == '_' }) {
            val hash = decrypter.next()

            val passwordPosition = hash.first.toString()

            if (passwordPosition.contains("[0-7]".toRegex())) {
                if (password[passwordPosition.toInt()] == '_') {
                    password[passwordPosition.toInt()] = hash.second
                }
            }
        }

        return password.joinToString("")
    }

    class Decrypter(val input: String) {

        private var password = "________".toCharArray()
        private var counter = 0
        private var hash = ""


        fun next(): Pair<Char, Char> {
            var beginning = ""
            while (beginning != "00000") {
                counter++
                hash = md5("$input$counter")
                beginning = hash.subSequence(0, 5).toString()
            }

            return Pair(hash[5], hash[6])
        }

        fun reset() {
            counter = 0
        }

        private fun md5(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

}

fun main() {
    val sample = "abc"
    val input = "wtnhxymk"

    Day05(input).solve()
}
