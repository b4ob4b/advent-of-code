package utils

import utils.IO.createNewDay
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolute

object IO {

    private val basePath = Path("").absolute()
    private val resourcesPath = "$basePath/src/main/resources"
    private val sourceCodePath = "$basePath/src/main/kotlin/com/baobab"

    enum class TYPE(val path: String) {
        SAMPLE("sample"), INPUT("input")
    }

    fun readFile(year: Int, day: Int, type: TYPE = TYPE.INPUT): String {
        val filePath = "$resourcesPath/$year/$day/${type.path}.txt"
        return File(filePath).readText()
    }

    fun createNewDay(year: Int, day: Int, createInputFiles: Boolean = true) {
        if (createInputFiles) createInputFiles(year, day)
        createKtFile(year, day, createInputFiles)
    }

    private fun createInputFiles(year: Int, day: Int) {
        val dir = File("$resourcesPath/$year/$day")
        if (dir.mkdir()) {
            if (File("$dir/sample.txt").createNewFile()) println("sample created")
            if (File("$dir/input.txt").createNewFile()) println("input created")
        }
    }

    private fun createKtFile(year: Int, day: Int, createInputFiles: Boolean) {
        val sday = if (day <= 9) "0$day" else "$day"
        val sample: String
        val input: String

        if (createInputFiles) {
            sample = "IO.readFile($year, $day, IO.TYPE.SAMPLE)"
            input = "IO.readFile($year, $day, IO.TYPE.INPUT)"
        } else {
            sample = "\"\""
            input = "\"\""
        }

        val text = """
            package com.baobab.y$year
            
            import utils.Day
            import utils.IO
            
            class Day$sday(val input: String): Day() {
                override fun part1(): Any? {
                    TODO("Not yet implemented")
                }

                override fun part2(): Any? {
                    TODO("Not yet implemented")
                }
            }
            
            fun main() {
                val sample = $sample
                val input = $input
                
                Day$sday(sample).solve()
            }
            
        """.trimIndent()

        val dir = File("$sourceCodePath/y$year")
        if (dir.isDirectory) {
            File("$dir/Day$sday.kt").writeText(text)
            println("Day$sday.kt created")
        }
    }


    fun <T> T.print() = println(this)
}

fun main() {
    createNewDay(2016, 5, false)
}