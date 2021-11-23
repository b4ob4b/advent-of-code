package utils

import utils.IO.createInputFiles
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolute

object IO {

    private val basePath = Path("").absolute()
    private val resourcesPath = "$basePath/src/main/resources/"

    enum class TYPE(val path: String) {
        SAMPLE("sample"), INPUT("input")
    }

    fun readFile(year: Int, day: Int, type: TYPE = TYPE.INPUT): String {
        val filePath = "$resourcesPath/$year/$day/${type.path}.txt"
        return File(filePath).readText()
    }

    fun createInputFiles(year: Int, day: Int) {
        val dir = File("$resourcesPath/$year/$day")
        if (dir.mkdir()) {
            File("$dir/sample.txt").createNewFile()
            File("$dir/input.txt").createNewFile()
        }
    }


    fun <T> T.print() = println(this)
}

fun main() {
    createInputFiles(2015, 19)
}