package org.hexworks.cobalt.events

interface Event {

    val key: String
        get() = this::class.simpleName
                ?: throw IllegalArgumentException("Event class doesn't have a name: ${this::class}")
}
