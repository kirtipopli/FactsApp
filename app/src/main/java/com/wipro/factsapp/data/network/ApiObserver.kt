package com.wipro.factsapp.data.network

import com.wipro.factsapp.data.network.model.ErrorEvent
import com.wipro.factsapp.data.network.model.SuccessEvent
import io.reactivex.observers.DisposableObserver

class ApiObserver<T> : DisposableObserver<T> {
    private var eventBus: EventBus
    private var requestCode: Int

    constructor(eventBus: EventBus, requestCode: Int) {
        this.eventBus = eventBus
        this.requestCode = requestCode
    }

    override fun onNext(t: T) {
        eventBus.onNext(SuccessEvent<T>(t, requestCode))
    }

    override fun onError(t: Throwable) {
        eventBus.onError(ErrorEvent(t, requestCode))
    }

    override fun onComplete() {

    }

}
