package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.core.internal.Atom
import org.hexworks.cobalt.core.internal.toAtom
import org.hexworks.cobalt.databinding.api.binding.*
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
        val binding: Binding<Foo> = unitUnderTest.bindOrElse(Foo(16))

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
        var changedCounter: Atom<Int> = 0.toAtom()
        val binding: Binding<Foo> = unitUnderTest.bindOrElseGet {
            changedCounter++
            Foo(22)
        }

        assertEquals(
            expected = 0,
            actual = changedCounter.get(),
            message = "Fetched default after binding"
        )

        prop.updateValue(Maybe.empty())

        assertEquals(
            expected = 1,
            actual = changedCounter.get(),
            message = "Fetched default after setting to empty Maybe"
        )

        assertEquals(
            expected = Foo(22),
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(-3)))

        // We expect 2 calls to the default getter here
        // because on every updateValue() the old value is also transformed one more time
        assertEquals(
            expected = 2,
            actual = changedCounter.get(),
            message = "Fetched default after setting value (NO more default!)"
        )
        assertEquals(
            expected = Foo(-3),
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(100)))

        assertEquals(
            expected = 2,
            actual = changedCounter.get(),
            message = "Fetched default after setting value again (NO more default!)"
        )
        assertEquals(
            expected = Foo(100),
            actual = binding.value
        )
    }

    @Test
    fun test_BindTransformOrElse() {
        val binding: Binding<Int> = unitUnderTest.bindTransformOrElse(17) { it.bar }

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
        var changedCounter: Atom<Int> = 0.toAtom()
        val binding: Binding<Int> = unitUnderTest.bindTransformOrElseGet({
            changedCounter++
            99
        }) { it.bar }

        prop.updateValue(Maybe.empty())

        assertEquals(
            expected = 1,
            actual = changedCounter.get()
        )
        assertEquals(
            expected = 99,
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(1337)))

        // We expect 2 calls to the default getter here
        // because on every updateValue() the old value is also transformed one more time
        assertEquals(
            expected = 2,
            actual = changedCounter.get()
        )
        assertEquals(
            expected = 1337,
            actual = binding.value
        )

        prop.updateValue(Maybe.of(Foo(1)))

        assertEquals(
            expected = 2,
            actual = changedCounter.get()
        )
        assertEquals(
            expected = 1,
            actual = binding.value
        )
    }
}

private operator fun Atom<Int>.inc(): Atom<Int> = also {
    transform { it + 1 }
}

private data class Foo(val bar: Int = 0)