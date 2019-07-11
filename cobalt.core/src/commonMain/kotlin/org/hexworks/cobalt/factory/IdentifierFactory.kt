package org.hexworks.cobalt.factory

import org.hexworks.cobalt.datatypes.Identifier

expect object IdentifierFactory {

    fun randomIdentifier(): Identifier

    fun fromString(str: String): Identifier
}
