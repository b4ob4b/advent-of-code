package com.baobab.sandbox

import kotlin.math.sqrt

class Prime {
    val primes = mutableListOf(2, 3)
    private var current = primes.last()
    val commonDivisors: MutableMap<Int, Set<Int>> = mutableMapOf()

    fun generate() = generateSequence {
        var notPrime = true
        while (notPrime) {
            current += 2
            notPrime = false
            for (number in primes) {
                if (current % number == 0) {
                    notPrime = true
                }
            }
        }
        primes.add(current)
        return@generateSequence current
    }

    fun getPrimesLessThan(value: Int): HashSet<Int> {
        while (this.generate().take(10).last() <= value) continue
        return primes.filter { it <= value }.toHashSet()
    }

    fun commonDivisorsOf(value: Int): Set<Int> {
        val divisors = mutableSetOf<Int>()
        val primes = getPrimesLessThan(sqrt(value.toFloat()).toInt() + 1)

        if (value in primes) {
            commonDivisors[value] = setOf(1, value)
            return commonDivisors[value]!!
        }

        if (value in commonDivisors.keys) {
            return commonDivisors[value]!!
        }

        for (prime in primes) {
            if (value % prime == 0) {
                divisors.add(prime)
                divisors.addAll(commonDivisorsOf(value / prime))
            }
        }
        divisors.add(1)
        divisors.add(value)
        commonDivisors[value] = divisors
        return divisors
    }

    /**
     * returns a list of prime factors only if prime factors don't exceed maxFactor
     *
     * */
    fun getFactorsOf(value: Int, maxFactor: Int = 20): List<Int> {
        val primes = getPrimesLessThan(maxFactor)
        var changed = true
        var rest = value

        val primeFactors = mutableListOf<Int>()

        while (changed) {
            changed = false
            for (prime in primes) {
                if (rest % prime != 0) continue
                changed = true
                rest = rest / prime
                primeFactors.add(prime)
            }
        }
        if (rest > 1) primeFactors.add(rest)
        return primeFactors

    }
}