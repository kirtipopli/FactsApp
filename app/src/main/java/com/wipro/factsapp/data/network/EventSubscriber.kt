package com.wipro.factsapp.data.network

interface EventSubscriber {
    fun onEvent(event: Event?)
}