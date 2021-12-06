package com.baobab.helpers

import kotlin.system.measureTimeMillis

fun Collection<Int>.product() = this.reduce { acc, i -> acc * i }

@JvmName("toMatrixT")
fun <T> List<List<T>>.toMatrix() = Matrix(this)

fun List<String>.toMatrix(separator: String = ""): Matrix<String> {
    return this.map { it.split(separator).filter { it.isNotEmpty() } }.toMatrix()
}

fun <T> Collection<T>.createCombinations(size: Int): Set<Collection<T>> {
    if (size == 1) return setOf(this)
    if (this.size == size) return setOf(this)
    val mSet = mutableSetOf<Collection<T>>()
    this.forEach { element ->
        mSet.addAll(this.filter { it != element }.createCombinations(size))
    }
    return mSet
}

fun <T> List<T>.combinations(size: Int): Sequence<List<T>> =
    when (size) {
        0 -> emptySequence()
        1 -> this@combinations.asSequence().map { listOf(it) }
        else -> sequence {
            this@combinations.forEachIndexed { index, element ->
                this@combinations.subList(index + 1, this@combinations.size).combinations(size - 1).forEach {
                    yield(listOf(element) + it)
                }
            }
        }
    }

fun main() {

    listOf(1, 2, 3).combinations(0).toList().print()

    for (size in 3..10) {
        val l = List(size) { it + 1 }
        val s1: Int
        val s2: Int
        val t1 = measureTimeMillis { s1 = l.createCombinations(3).size }
        val t2 = measureTimeMillis { s2 = l.combinations(3).toList().size }

        println(size)
        println("$s1: $t1")
        println("$s2: $t2")
        println()
    }
}