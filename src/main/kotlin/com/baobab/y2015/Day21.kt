package com.baobab.y2015

import com.baobab.helpers.combinations
import utils.Day

class Day21(val players: Pair<Game.Character, Game.Character>) : Day() {
    override fun part1(): Int {
        return Game(players).play().minOf { character ->
            character.items.sumOf { it.cost }
        }
    }

    override fun part2(): Any? {
        TODO("Not yet implemented")
    }

    class Game(players: Pair<Character, Character>) {

        private val player = players.first
        private val boss = players.second
        var round = 0


        fun play(): Sequence<Character> {
            return player.goesShopping().filter { p ->
                p winsAgainst boss
            }
        }


        object Shop {
            open class Item(name: String, open val cost: Int, open val damage: Int, open val armor: Int)
            data class Weapon(val name: String, override val cost: Int, override val damage: Int) : Item(name, cost, damage, 0)
            data class Armor(val name: String, override val cost: Int, override val armor: Int) : Item(name, cost, 0, armor)
            data class Ring(val name: String, override val cost: Int, override val damage: Int, override val armor: Int) :
                Item(name, cost, damage, armor)

            val weapons = listOf(
                Weapon("Dagger", 8, 4),
                Weapon("Shortsword", 10, 5),
                Weapon("Warhammer", 25, 6),
                Weapon("Longsword", 40, 7),
                Weapon("Greataxe", 74, 8),
            )

            val armors = listOf(
                Armor("Leather", 13, 1),
                Armor("Chainmail", 31, 2),
                Armor("Splintmail", 53, 3),
                Armor("Bandedmail", 75, 4),
                Armor("Platemail", 102, 5),
            )

            val rings = listOf(
                Ring("Damage +1", 25, 1, 0),
                Ring("Damage +2", 50, 2, 0),
                Ring("Damage +3", 100, 3, 0),
                Ring("Defense +1", 20, 0, 1),
                Ring("Defense +2", 40, 0, 2),
                Ring("Defense +3", 80, 0, 3),
            )
        }

        data class Character(
            val name: String,
            var hp: Int,
            var damage: Int,
            var armor: Int,
            var items: MutableList<Shop.Item> = mutableListOf()
        ) {
            private val initialHp = hp
            private val initialDamage = damage
            private val initialArmor = armor

            private fun reset() {
                hp = initialHp
                damage = initialDamage
                armor = initialArmor
                items = mutableListOf()
            }

            infix fun buys(items: List<Shop.Item>): Int {
                return items.sumOf { item ->
                    this.damage += item.damage
                    this.armor += item.armor
                    this.items.add(item)
                    item.cost
                }
            }

            infix fun attacks(character: Character) {
                val attackDamage = this.damage - character.armor
                character.hp = character.hp - attackDamage
            }

            infix fun winsAgainst(boss: Character): Boolean {
                val player = this
                var turn = 0
                boss.reset()
                while (player.hp > 0 && boss.hp > 0) {
                    if (turn % 2 == 0) {
                        player attacks boss
                    } else {
                        boss attacks player
                    }
                    turn++
                }
                return player.hp > 0
            }

            fun goesShopping() = sequence<Character> {
                val character = this@Character
                val amountWeapons = 1
                val amountArmors = 1
                val amountRings: IntRange = 0..2

                val weaponCombinations = Shop.weapons.combinations(amountWeapons)
                weaponCombinations.forEach { weapon ->
                    val armorCombinations = Shop.armors.combinations(amountArmors)
                    armorCombinations.forEach { armor ->
                        amountRings.forEach {
                            val ringCombinations = Shop.rings.combinations(it)
                            if (!ringCombinations.iterator().hasNext()) {
                                var cost = 0
                                character.reset()
                                cost += character buys weapon
                                cost += character buys armor
                                yield(character)
                            } else {
                                ringCombinations.forEach { rings ->
                                    var cost = 0
                                    character.reset()
                                    cost += character buys weapon
                                    cost += character buys armor
                                    cost += character buys rings
                                    yield(character)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

fun main() {
    val testBoss = Day21.Game.Character("test boss", 12, 7, 2)
    val testPlayer = Day21.Game.Character("test player", 8, 5, 5)
    val sample = testPlayer to testBoss

    val boss = Day21.Game.Character("boss", 109, 8, 2)
    val player = Day21.Game.Character("player", 100, 0, 0)
    val input = player to boss

    Day21(input).solve()
}
