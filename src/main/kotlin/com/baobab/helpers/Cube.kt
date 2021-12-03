package com.baobab.helpers

data class Cube(val x: Int, val y: Int, val z: Int) {
    val sides = listOf(x, y, z)

    fun getFaceArea() = sides
        .sorted()
        .take(2)
        .sumOf { it * 2 }

    fun getSurfaceArea(): Int {
        return 2 * (x * y + x * z + y * z)
    }

    fun getVolume() = sides.product()
}