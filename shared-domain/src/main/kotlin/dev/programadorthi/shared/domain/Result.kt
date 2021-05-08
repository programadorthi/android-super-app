/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found
 * in the license/LICENSE.txt file.
 */

@file:Suppress("UNCHECKED_CAST", "RedundantVisibilityModifier")

package dev.programadorthi.shared.domain

import java.io.Serializable

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or a failure with an arbitrary [Throwable] exception.
 */
class Result<out T> internal constructor(
    internal val value: Any?
) : Serializable {
    // discovery

    /**
     * Returns `true` if this instance represents a successful outcome.
     * In this case [isFailure] returns `false`.
     */
    val isSuccess: Boolean get() = value !is Failure && isBusiness.not()

    /**
     * Returns `true` if this instance represents a failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    val isFailure: Boolean get() = value is Failure

    val isBusiness: Boolean get() = value is Business

    // value & exception retrieval

    /**
     * Returns the encapsulated value if this instance represents [success][Result.isSuccess]
     * or `null` if it is [failure][Result.isFailure].
     */
    fun getOrNull(): T? =
        when {
            isFailure || isBusiness -> null
            else -> value as T
        }

    /**
     * Returns the encapsulated [Throwable] exception if this instance represents
     * [failure][isFailure] or `null` if it is [success][isSuccess].
     */
    fun exceptionOrNull(): Throwable? =
        when (value) {
            is Failure -> value.exception
            else -> null
        }

    fun businessOrNull(): Business? =
        when (value) {
            is Business -> value
            else -> null
        }

    /**
     * Returns a string `Success(v)` if this instance represents [success][Result.isSuccess]
     * where `v` is a string representation of the value or a string `Failure(x)` if
     * it is [failure][isFailure] where `x` is a string representation of the exception.
     */
    override fun toString(): String =
        when (value) {
            is Failure -> value.toString() // "Failure($exception)"
            is Business -> "Business($value)"
            else -> "Success($value)"
        }

    // companion with constructors

    /**
     * Companion object for [Result] class that contains its constructor functions
     * [success] and [failure].
     */
    companion object {
        // FIXME: Create a valid number using:
        //  https://docs.oracle.com/javase/8/docs/platform/serialization/spec/class.html#a4100
        private const val serialVersionUID = 1L

        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        @JvmName("success")
        fun <T> success(value: T): Result<T> = Result(value)

        /**
         * Returns an instance that encapsulates the given [Throwable] [exception] as failure.
         */
        fun <T> failure(exception: Throwable): Result<T> = Result(createFailure(exception))

        fun <T, B : Business> business(value: B): Result<T> = Result(value)
    }

    internal class Failure(
        @JvmField
        val exception: Throwable
    ) : Serializable {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
        companion object {
            // FIXME: Create a valid number using:
            //  https://docs.oracle.com/javase/8/docs/platform/serialization/spec/class.html#a4100
            private const val serialVersionUID = 2L
        }
    }

    interface Business
}

/**
 * Creates an instance of internal marker [Result.Failure] class to
 * make sure that this class is not exposed in ABI.
 */
internal fun createFailure(exception: Throwable): Any =
    Result.Failure(exception)

// -- extensions ---

/**
 * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or the
 * [defaultValue] if it is [failure][Result.isFailure].
 */
fun <R, T : R> Result<T>.getOrDefault(defaultValue: R): R {
    if (isFailure) return defaultValue
    return value as T
}
