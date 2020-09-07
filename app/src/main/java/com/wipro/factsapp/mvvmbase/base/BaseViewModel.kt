package com.wipro.factsapp.mvvmbase.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wipro.factsapp.data.network.callback.Resource
import com.wipro.factsapp.utils.NetworkHelper

abstract class BaseViewModel(
    val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    abstract fun onCreate()
}