package com.wipro.factsapp.mvvmbase.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wipro.factsapp.R
import com.wipro.factsapp.data.network.callback.Resource
import com.wipro.factsapp.mvvmbase.rx.SchedulerProvider
import com.wipro.factsapp.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    abstract fun onCreate()
}