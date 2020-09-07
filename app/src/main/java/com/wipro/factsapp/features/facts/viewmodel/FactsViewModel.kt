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

class FactsViewModel(
    networkHelper: NetworkHelper,
    private val factsRepository: FactsRepository
) : BaseViewModel(networkHelper) {

    val isRefreshingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val factsResponse: MutableLiveData<Resource<FactsResponse>> = MutableLiveData()

    override fun onCreate() {}

    fun getAllFacts() = viewModelScope.launch {
        factsResponse.postValue(Resource.loading())
        val response = factsRepository.getAllFacts()
        isRefreshingLiveData.postValue(false)
        factsResponse.postValue(handleFactsResponse(response))
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

}