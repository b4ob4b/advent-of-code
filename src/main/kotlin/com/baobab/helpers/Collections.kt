package com.baobab.helpers

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