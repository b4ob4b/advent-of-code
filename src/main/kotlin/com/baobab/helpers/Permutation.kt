package com.baobab.helpers

fun <T> List<T>.permuteRecursive(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val perms = mutableListOf<List<T>>()
    val toInsert = this[0]
    for (perm in this.drop(1).permuteRecursive()) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}

fun <T> List<T>.permutations(): Sequence<List<T>> =
    when (size) {
        0 -> emptySequence()
        1 -> sequenceOf(toList())
        else -> {
            val head = first()
            val tail = drop(1)
            sequence {
                tail.permutations().forEach { perm ->
                    for (i in 0..perm.size) {
                        yield(perm.toMutableList().apply { add(i, head) })
                    }
                }
            }
        }
    }
