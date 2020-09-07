package com.wipro.factsapp.features.facts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wipro.factsapp.data.dao.repository.FactsRepository
import com.wipro.factsapp.data.network.callback.Resource
import com.wipro.factsapp.features.facts.model.FactsResponse
import com.wipro.factsapp.mvvmbase.base.BaseViewModel
import com.wipro.factsapp.utils.NetworkHelper
import kotlinx.coroutines.launch
import retrofit2.Response

class FactsFragmentViewModel(
    networkHelper: NetworkHelper,
    private val factsRepository: FactsRepository
) : BaseViewModel(networkHelper) {

    val isRefreshingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val factsResponse: MutableLiveData<Resource<FactsResponse>> = MutableLiveData()
    val isInternetConnected: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {}

    fun getAllFacts() = viewModelScope.launch {
        factsResponse.postValue(Resource.loading())
        isRefreshingLiveData.postValue(false)

        if (isInternetConnected()) {
            isInternetConnected.postValue(true)
            val response = factsRepository.getAllFacts()
            factsResponse.postValue(handleFactsResponse(response))
        } else {
            isInternetConnected.postValue(false)
        }

    }

    fun handleFactsResponse(response: Response<FactsResponse>?): Resource<FactsResponse> {
        if (response != null) {
            if (response.isSuccessful) {
                response.body().let { resultResponse ->
                    return Resource.success(resultResponse)
                }
            }
        }
        return Resource.error(response?.body())
    }

    fun isInternetConnected(): Boolean = checkInternetConnection()
}