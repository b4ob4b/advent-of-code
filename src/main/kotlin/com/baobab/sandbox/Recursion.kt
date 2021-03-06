package utils

fun fib(n: Int, memo: HashMap<Int, Int> = hashMapOf()): Int {
    if (n in memo.keys) return memo.get(n)!!
    if (n <= 2) return 1
    memo.put(n, fib(n - 2, memo) + fib(n - 1, memo))
    return memo.get(n)!!
}

fun <T> List<T>.allIndicesOf(element: T): List<Int> {
    return this.foldIndexed(mutableListOf()) { index, acc, i ->
        if (i == element) {
            acc.add(index)
        }
        acc
    }
}

fun <T> List<T>.compositions(minSize: Int = 1): List<List<T>> {
    if (this.size <= minSize) return listOf(this)
    val perms: MutableList<List<T>> = mutableListOf()
    for (firstElement in this) {
        val rest = this.toMutableList()
        rest.remove(firstElement)
        for (r in rest.compositions(minSize)) {
            val perm = mutableListOf(firstElement)
            perm.addAll(r)
            perms.add(perm)
        }
    }
    return perms
}

/**
 * Split an Integer in n combinations
 *
 * @param n the number of parts.
 * @return list of all combinations
 */
fun Int.splitIntoCombinations(n: Int): List<List<Int>> {
    if (n <= 1) return listOf(listOf(this))
    val collect: MutableList<MutableList<Int>> = mutableListOf(mutableListOf())
    for (i in (1..(this - (n - 1)))) {
        val rest = this - i
        for (list in rest.splitIntoCombinations(n - 1)) {
            collect.add(mutableListOf(i).also { it.addAll(list) })
        }
    }
    return collect.filter { it.size == n }
}
