package com.wipro.factsapp.data.network

interface EventBus {
    fun subscribe(subscriber: EventSubscriber?)
    fun unsubscribe()
    fun onNext(event: Event?)
    fun onError(event: Event?)
    fun onCompletion(event: Event?)
}