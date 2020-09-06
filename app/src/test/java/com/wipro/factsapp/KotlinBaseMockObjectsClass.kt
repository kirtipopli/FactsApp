package com.wipro.factsapp

import com.wipro.factsapp.mvvmbase.network.NetworkService
import com.wipro.factsapp.mvvmbase.rx.SchedulerProvider
import com.wipro.factsapp.utils.NetworkHelper
import com.wipro.factsapp.utils.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class KotlinBaseMockObjectsClass {
    val mockNetworkService = mock<NetworkService>()
    val mockSchedulerProvider = mock<SchedulerProvider>()
    val testSchedulerProvider = TestSchedulerProvider()
    val mockCompositeDisposable = mock<CompositeDisposable>()
    val mockNetworkHelper = mock<NetworkHelper>()
}