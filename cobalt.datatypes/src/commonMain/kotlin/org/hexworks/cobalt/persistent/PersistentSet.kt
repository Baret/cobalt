package org.hexworks.cobalt.persistent

interface PersistentSet<E> {

    val size: Int

    fun isEmpty(): Boolean

    operator fun contains(element: E): Boolean

    fun iterator(): Iterator<E>

    fun containsAll(elements: Collection<E>): Boolean

}
