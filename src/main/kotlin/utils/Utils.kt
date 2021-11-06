package utils

import java.io.File

class Utils {

    companion object {
        fun readFile(year: Int, day: Int, type: String = "input"): List<Any> {
            val filePath = "/home/julian/Documents/hacking/adventofcode/src/main/resources/$year/$type.$day.txt"
            return File(filePath)
                .readLines()
                .first()
                .split(", ")
        }
        fun readFile2(year: Int, day: Int, type: String = "input"): String {
            val filePath = "/home/julian/Documents/hacking/adventofcode/src/main/resources/$year/$type.$day.txt"
            return File(filePath).readText()
        }
    }

}
