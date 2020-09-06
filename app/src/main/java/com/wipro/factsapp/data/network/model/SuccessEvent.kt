package com.wipro.factsapp.data.network.model

import com.wipro.factsapp.data.network.Event
import com.wipro.factsapp.data.network.Event.Companion.TYPE_SUCCESS

class SuccessEvent<T> : Event {
    private var result: T
    override var requestCode: Int

    constructor(result: T, requestCode: Int) {
        this.result = result
        this.requestCode = requestCode
    }


    override val type: Int
        get() = TYPE_SUCCESS

    override fun <T> getResult(): T {
        return result as T
    }

}