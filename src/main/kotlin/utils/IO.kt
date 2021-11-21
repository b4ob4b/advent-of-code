package utils

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolute

object IO {

    enum class TYPE(val path: String) {
        SAMPLE("sample"), INPUT("input")
    }

    fun readFile(year: Int, day: Int, type: TYPE = TYPE.INPUT): String {
        val basePath = Path("").absolute()
        val filePath = "$basePath/src/main/resources/$year/$day/${type.path}.txt"
        return File(filePath).readText()
    }

    fun <T> T.print() = println(this)
}