package com.wipro.factsapp.data.network.model

import com.wipro.factsapp.data.network.Event
import com.wipro.factsapp.data.network.Event.Companion.TYPE_ERROR

class ErrorEvent : Event {
    var result: Throwable
    override var requestCode = 0

    constructor(
        throwable: Throwable,
        requestCode: Int
    ) {
        result = throwable
        this.requestCode = requestCode
    }

    constructor(throwable: Throwable) {
        result = throwable
    }

    override val type: Int
        get() = TYPE_ERROR

    override fun <T> getResult(): T? {
        return null
    }
}