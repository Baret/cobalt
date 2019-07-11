package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.value.ObservableValue

interface ObservableList<T : Any> : MutableList<T>, ObservableValue<ObservableList<T>>
