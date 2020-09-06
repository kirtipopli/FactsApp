package com.wipro.factsapp

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
inline fun <reified T> spy(): T = Mockito.spy(T::class.java)
inline fun <T> whenever(methodCall: T): OngoingStubbing<T> =
    Mockito.`when`(methodCall)