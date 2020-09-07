package com.wipro.factsapp

import com.wipro.factsapp.mvvmbase.network.NetworkService
import com.wipro.factsapp.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class KotlinBaseMockObjectsClass {
    val mockNetworkService = mock<NetworkService>()
    val mockNetworkHelper = mock<NetworkHelper>()
}