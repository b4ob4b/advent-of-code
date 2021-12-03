package com.baobab.helpers

fun <T> T.print() = println(this)

fun String.splitLines() = split("\n")

fun String.toBinary() = Integer.parseInt(this, 2)

fun Integer.toBinary() = this.toString().toBinary()