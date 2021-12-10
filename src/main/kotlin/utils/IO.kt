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
        val formattedDay = day.toString().padStart(2, '0')
        val text = """
            package com.baobab.y$year
            
            import utils.AocDay
            import utils.IO
            
            fun main() {
                Day$formattedDay().solve()
            }
            
            class Day$formattedDay: AocDay(inputType = IO.TYPE.SAMPLE) {
                override fun part1(): Any? {
                    return "not yet implement"
                }

                override fun part2(): Any? {
                    return "not yet implement"
                }
            }           
        """.trimIndent()

        val dir = File("$sourceCodePath/y$year")
        if (dir.isDirectory) {
            val file = File("$dir/Day$formattedDay.kt")
            if (!file.exists()) {
                file.writeText(text)
                println("Day$formattedDay.kt created")
            } else {
                println("Day$formattedDay.kt already exists")
            }
        }
    }
}

fun main() {
    createNewDay(2021, 10, true)
}