package com.baobab.sandbox

class LinkedList<T>(val value: T) {
    val head: Node<T> = Node(value)
    var tail: Node<T>? = null
    var currentNode = Node(value)
    var size = 1

    fun add(value: T): LinkedList<T> {
        size++
        val newNode = Node(value)
        val nextNode = currentNode.next

        currentNode.next = newNode
        newNode.next = nextNode

        if (currentNode.value == head.value) {
            head.next = newNode
        }
        if (currentNode == tail || tail == null) {
            tail = newNode
        }

        return this
    }

    fun add(values: Collection<T>): LinkedList<T> {
        values.reversed().forEach(this::add)
        return this
    }

    fun hasNext(): Boolean {
        return currentNode.next != null
    }

    fun next(): Node<T> {
        if (hasNext()) {
            currentNode = currentNode.next!!
        }
        return currentNode
    }

    fun reset() {
        currentNode = head
    }

    fun search(value: T): Node<T>? {
        reset()
        while (hasNext()) {
            if (currentNode.value == value) return currentNode
            next()
        }
        return null
    }

    override fun toString(): String {
        return "LinkedList(head=$head, currentNode=$currentNode, tail=$tail)"
    }
}

data class Node<T>(val value: T, var next: Node<T>?) {
    constructor(value: T) : this(value, null)

    override fun toString(): String {
        return "Node(value=$value, next=${next?.value})"
    }
}
