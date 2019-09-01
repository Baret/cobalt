package org.hexworks.cobalt.persistent

import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.persistent.impl.HashArrayMappedTrie

interface PersistentMap<K, V> {

    /**
     * Returns the number of key/value pairs in the map.
     */
    val size: Int

    /**
     * Returns the value corresponding to the given [key], or `null` if such a key is not present in the map.
     */
    operator fun get(key: K): Maybe<out V>

    /**
     * Returns a read-only [Set] of all keys in this map.
     */
    val keys: Set<K>

    /**
     * Returns a read-only [Collection] of all values in this map. Note that this collection
     * may contain duplicate values.
     */
    val values: Collection<V>

    /**
     * Returns a read-only [Set] of all key/value pairs in this map.
     */
    val entries: Set<Pair<K, V>>

    /**
     * Returns `true` if the map is empty (contains no elements), `false` otherwise.
     */
    fun isEmpty(): Boolean = entries.isEmpty()

    /**
     * Returns `true` if the map contains the specified [key].
     */
    fun containsKey(key: K): Boolean = keys.any { it == key }

    /**
     * Returns `true` if the map maps one or more keys to the specified [value].
     */
    fun containsValue(value: V): Boolean = values.any { it == value }

    /**
     * Returns the value corresponding to the given [key], or [defaultValue] if such a key
     * is not present in the map.
     */
    fun getOrElse(key: K, defaultValue: V): V {
        return if (containsKey(key)) get(key).get()
        else defaultValue
    }

    companion object {

        fun <K, V> create(): PersistentMap<K, V> = HashArrayMappedTrie.empty()
    }
}
