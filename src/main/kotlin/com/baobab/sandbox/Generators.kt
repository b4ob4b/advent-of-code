package utils

import com.baobab.helpers.print


fun fib(n: Int) = generateSequence(Pair(1, 1)) {
    Pair(it.second, it.first + it.second)
}.map { it.first }.take(n).toList()

fun fibonacciSequence() = sequence {
    var terms = Pair(1, 1)

    while (true) {
        yield(terms.first)
        terms = terms.second to terms.first + terms.second
    }
}

fun main() {
    fibonacciSequence().take(10).toList().print()
}