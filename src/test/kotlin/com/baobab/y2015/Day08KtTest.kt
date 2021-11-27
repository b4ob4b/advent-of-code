package com.baobab.y2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.IO


internal class Day08KtTest : Day08("") {
    @Test
    fun test() {
//        "" is 2 characters of code (the two double quotes), but the string contains zero characters.
//        "abc" is 5 characters of code, but 3 characters in the string data.
//        "aaa\"aaa" is 10 characters of code, but the string itself contains six "a" characters and a single, escaped quote character, for a total of 7 characters in the string data.
//        "\x27" is 6 characters of code, but the string itself contains just one - an apostrophe ('), escaped using hexadecimal notation.

        val input = IO.readFile(2015, 8, IO.TYPE.SAMPLE)

        Day08(input).lines.let {
            Assertions.assertEquals(2, it[0].length)
            Assertions.assertEquals(5, it[1].length)
            Assertions.assertEquals(10, it[2].length)
            Assertions.assertEquals(6, it[3].length)
            Assertions.assertEquals(4, it[4].length)
            Assertions.assertEquals(6, it[5].length)
            Assertions.assertEquals(6, it[6].length)
        }

        Day08(input).lines.let {
            Assertions.assertEquals(0, it[0].lengthDecoded())
            Assertions.assertEquals(3, it[1].lengthDecoded())
            Assertions.assertEquals(7, it[2].lengthDecoded())
            Assertions.assertEquals(1, it[3].lengthDecoded())
            Assertions.assertEquals(1, it[4].lengthDecoded())
            Assertions.assertEquals(2, it[5].lengthDecoded())
            Assertions.assertEquals(2, it[6].lengthDecoded())
        }
    }

    @Test
    fun test2() {
        val input = IO.readFile(2015, 8, IO.TYPE.SAMPLE)

        Day08(input).lines.let {
            Assertions.assertEquals(6, it[0].lengthEncoded())
            Assertions.assertEquals(9, it[1].lengthEncoded())
            Assertions.assertEquals(16, it[2].lengthEncoded())
            Assertions.assertEquals(11, it[3].lengthEncoded())
        }
    }
}
