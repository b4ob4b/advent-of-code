package utils

import com.baobab.helpers.print


fun fib(n: Int) = generateSequence(Pair(1, 1)) {
    Pair(it.second, it.first + it.second)
}.map { it.first }.take(n).toList()

fun main() {
    fib(10).print()
}