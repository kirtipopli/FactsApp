package com.wipro.factsapp.data.network

interface Event {
    val type: Int

    fun <T> getResult(): T?
    val requestCode: Int

    companion object {
        const val TYPE_SUCCESS = 1
        const val TYPE_ERROR = 2
    }

}