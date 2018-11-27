package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.datatypes.Maybe
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

class DefaultThreadSafeQueue<E>(private val backend: BlockingDeque<E> = LinkedBlockingDeque())
    : ThreadSafeQueue<E>, MutableCollection<E> by backend {

    override fun offer(e: E): Boolean = backend.offer(e)

    override fun drainTo(c: MutableCollection<E>): Int = backend.drainTo(c)

    override fun drainAll(): Collection<E> {
        return mutableListOf<E>().also {
            backend.drainTo(it)
        }
    }

    override fun remove(element: E): Boolean = backend.remove(element)

    override fun peek(): Maybe<E> = Maybe.ofNullable(backend.peek())

    override fun poll(): Maybe<E> = Maybe.ofNullable(backend.poll())

    override fun pollLast(): Maybe<E> = Maybe.ofNullable(backend.pollLast())
}
