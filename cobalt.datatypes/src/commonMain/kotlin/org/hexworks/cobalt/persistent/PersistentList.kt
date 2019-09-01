package org.hexworks.cobalt.persistent

interface PersistentList<E> {

    val size: Int

    fun isEmpty(): Boolean
    fun contains(element: E): Boolean
    fun iterator(): Iterator<E>

    fun containsAll(elements: Collection<E>): Boolean

    /**
     * Returns the element at the specified index in the list.
     */
    operator fun get(index: Int): E

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if the specified
     * element is not contained in the list.
     */
    fun indexOf(element: E): Int

    /**
     * Returns the index of the last occurrence of the specified element in the list, or -1 if the specified
     * element is not contained in the list.
     */
    fun lastIndexOf(element: E): Int
}
