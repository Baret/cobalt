package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.binding.bindOrElse
import org.hexworks.cobalt.databinding.api.binding.bindOrElseGet
import org.hexworks.cobalt.databinding.api.binding.bindTransformOrElse
import org.hexworks.cobalt.databinding.api.binding.bindTransformOrElseGet
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.datatypes.Maybe
import kotlin.test.Test
import kotlin.test.assertEquals

class MaybeBindingTest {

    private val prop: Property<Maybe<Foo>> = createPropertyFrom(Maybe.of(Foo()))

    private val unitUnderTest: ObservableValue<Maybe<Foo>> = prop

    @Test
    fun test_BindOrElse() {
        val binding = unitUnderTest.bindOrElse(Foo(16))

        prop.updateValue(Maybe.empty())

        assertEquals(
            expected = Foo(16),
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(12)))

        assertEquals(
            expected = Foo(12),
            actual = binding.value
        )
    }

    @Test
    fun test_BindOrElseGet() {
        var changedCounter = 0
        val binding = unitUnderTest.bindOrElseGet {
            changedCounter++
            Foo(22)
        }

        assertEquals(0, changedCounter)

        prop.updateValue(Maybe.empty())

        assertEquals(1, changedCounter)
        assertEquals(
            expected = Foo(22),
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(-3)))

        assertEquals(1, changedCounter)
        assertEquals(
            expected = Foo(-3),
            actual = binding.value
        )
    }

    @Test
    fun test_BindTransformOrElse() {
        val binding = unitUnderTest.bindTransformOrElse(17) { it.bar }

        prop.updateValue(Maybe.empty())

        assertEquals(
            expected = 17,
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(125)))

        assertEquals(
            expected = 125,
            actual = binding.value
        )
    }

    @Test
    fun test_BindTransformOrElseGet() {
        var changedCounter = 0
        val binding = unitUnderTest.bindTransformOrElseGet({
            changedCounter++
            99
        }) { it.bar }

        prop.updateValue(Maybe.empty())

        assertEquals(1, changedCounter)
        assertEquals(
            expected = 17,
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(1337)))

        assertEquals(1, changedCounter)
        assertEquals(
            expected = 1337,
            actual = binding.value
        )
    }
}

private data class Foo(val bar: Int = 5)