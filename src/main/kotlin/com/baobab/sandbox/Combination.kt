package com.baobab.sandbox

import com.baobab.helpers.print


class Combination<T : Comparable<T>>() {
    val memo: MutableMap<String, Set<List<T>>> = mutableMapOf()

    fun of(list: List<T>, minimumSize: Int = 1): Set<List<T>> {
        val mList = list.toMutableList()
        mList.sort()
        val key = list.joinToString(",")
        if (list.size <= minimumSize) {
            memo[key] = setOf(list)
            return setOf(list)
        }
        if (memo.containsKey(key)) return memo[key]!!

        val combinations: MutableSet<List<T>> = mutableSetOf(mList)

        for (element in mList) {
            val rest = mList.toMutableList()
            rest.remove(element)
            for (r in this.of(rest)) {
                combinations.add(r)
            }
            combinations.add(rest)
        }
        memo[key] = combinations
        return combinations
    }
}

fun main() {
    Combination<Int>().of(listOf(1, 2, 3, 4, 5), 1).print()
}