package com.baobab.y2015

import utils.Day
import utils.splitIntoCombinations

class Day15(val input: String) : Day() {
    val ingredients = input.split("\n").map { Ingredient.fromString(it) }

    companion object {
        const val TOP_SCORE = 100
        const val CALORIES = 500
    }

    val combinations = TOP_SCORE.splitIntoCombinations(ingredients.size)

    override fun part1(): Any? {
        var result = 0
        for (combination in combinations) {
            val score = calculateScore(combination)
            if (score > result) {
                result = score
            }
        }
        return result
    }

    private fun calculateScore(combination: List<Int>): Int {
        val capacities = mutableListOf(0)
        val durabilities = mutableListOf(0)
        val flavors = mutableListOf(0)
        val textures = mutableListOf(0)
        for (i in 0 until ingredients.size) {
            capacities.add(ingredients.get(i).capacity * combination.get(i))
            durabilities.add(ingredients.get(i).durability * combination.get(i))
            flavors.add(ingredients.get(i).flavor * combination.get(i))
            textures.add(ingredients.get(i).texture * combination.get(i))
        }
        return capacities.sum().zeroIfNegative() * durabilities.sum().zeroIfNegative() * flavors.sum()
            .zeroIfNegative() * textures.sum().zeroIfNegative()

    }

    private fun Int.zeroIfNegative() = if (this < 0) 0 else this


    override fun part2(): Any? {
        var result = 0
        combinations.filter {
            val calories = mutableListOf(0)
            for (i in 0 until ingredients.size) {
                calories.add(ingredients.get(i).calories * it.get(i))
            }
            calories.sum() == CALORIES
        }.forEach {
            val score = calculateScore(it)
            if (score > result) {
                result = score
            }
        }
        return result
    }

}

data class Ingredient(
    val name: String,
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int,
) {
    companion object {
        fun fromString(string: String): Ingredient {
            val parts = string.split(" ")
            return Ingredient(
                parts[0].replace(":", ""),
                parts[2].replace(",", "").toInt(),
                parts[4].replace(",", "").toInt(),
                parts[6].replace(",", "").toInt(),
                parts[8].replace(",", "").toInt(),
                parts[10].replace(",", "").toInt(),
            )
        }
    }
}

fun main() {
    val sample = """
        Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
        Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
    """.trimIndent()

    val input = """
        Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2
        Sprinkles: capacity -3, durability 3, flavor 0, texture 0, calories 9
        Candy: capacity -1, durability 0, flavor 4, texture 0, calories 1
        Chocolate: capacity 0, durability 0, flavor -2, texture 2, calories 8
    """.trimIndent()
    Day15(input).solve()
}