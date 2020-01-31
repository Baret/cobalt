@file:Suppress("TestFunctionName")

package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.expression.bindIsEmpty
import org.hexworks.cobalt.databinding.api.expression.bindNot
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName")
class StringExpressionsTest {

    @Test
    fun When_property_is_empty_binding_should_have_true_value() {
        val prop = DefaultProperty("")

        val binding = prop.bindIsEmpty()

        assertEquals(expected = true, actual = binding.value)
    }

    @Test
    fun When_property_is_no_longer_empty_binding_should_have_false_value() {
        val prop = DefaultProperty("")

        val binding = prop.bindIsEmpty()

        prop.value = "foo"

        assertEquals(expected = false, actual = binding.value)
    }

    @Test
    fun When_property_is_not_empty_binding_should_have_true_value() {
        val prop = DefaultProperty("")

        val binding = prop.bindIsEmpty().bindNot()

        assertEquals(expected = false, actual = binding.value)
    }

    @Test
    fun When_property_is_empty_binding_should_have_false_value() {
        val prop = DefaultProperty("")

        val binding = prop.bindIsEmpty().bindNot()

        prop.value = "foo"

        assertEquals(expected = true, actual = binding.value)
    }

}
