package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.datatypes.Maybe

/**
 * The resulting binding contains the value of the observed [Maybe] or [default] when it is empty.
 */
fun <T : Any> ObservableValue<Maybe<T>>.bindOrElse(default: T): Binding<T> =
    bindTransform { it.orElse(default) }

/**
 * The resulting binding contains the value of the observed [Maybe] or the value supplied by
 * [default] when it is empty.
 */
fun <T : Any> ObservableValue<Maybe<T>>.bindOrElseGet(default: () -> T): Binding<T> =
    bindTransform { it.orElseGet(default) }

/**
 * The binding will contain the [Maybe]'s value transformed with [transformer] or [default] when it is empty.
 */
fun <T : Any, S: Any> ObservableValue<Maybe<T>>.bindTransformOrElse(default: S, transformer: (T) -> S): Binding<S> =
    bindTransform { it.map(transformer).orElse(default) }

/**
 * The binding will contain the [Maybe]'s value transformed with [transformer] or the value supplied
 * by [default] when it is empty.
 */
fun <T : Any, S: Any> ObservableValue<Maybe<T>>.bindTransformOrElseGet(default: () -> S, transformer: (T) -> S): Binding<S> =
    bindTransform { it.map(transformer).orElseGet(default) }