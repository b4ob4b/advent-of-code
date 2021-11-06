package com.baobab.y2020//import java.io.File
//
//fun readFile(path: String): List<String> = File(path).bufferedReader().readLines()
//
//fun main() {
//    val data = readFile("src/main/resources/2020/11/test.txt")
//
//    var field = data.map { it.toCharArray() }
//    var nextField = field.toMutableList()
//
//    var counter = 0
//    while (true) {
//        var change = false
//        for (r in field.indices) {
//            for (c in field[0].indices) {                var neighbours = 0
//                for (dr in listOf(-1, 0, 1)) {
//                    for (dc in listOf(-1, 0 ,1)) {
//                        if (dr == 0 && dc == 0) {
//                            continue
//                        }
//                        if (0 <= dr && dr <= (field.size - 1) && 0 <= dc && dc <= (field[0].size - 1) && field[dr][dc] == '#') {
//
//                            println("$r, $c, $dr, $dc, ${nextField.get(dr).get(dc)}")
//                            neighbours ++
//                        }
//                    }
//                }
////                println("${field[r][c]}: $neighbours")
//                if ('L' == field[r][c] && neighbours == 0){
//                    nextField[r][c] = '#'
//                    change = true
//                }
//                if ('#' == field[r][c] && neighbours >= 4){
//                    nextField[r][c] = 'L'
//                    change = true
//                }
//            }
//        }
//        field = nextField
//        println(counter++)
//        field.forEach { it.forEach { print(it) } }
//        println()
//        nextField.forEach { it.forEach { print(it) } }
//        if (!change){
//            break
//        }
//    }
//
//}