package com.wipro.factsapp.data.dao.repository

import com.wipro.factsapp.data.network.ServerResponse
import com.wipro.factsapp.features.facts.model.FactsResponse
import com.wipro.factsapp.mvvmbase.network.NetworkService
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FactsRepository @Inject constructor(
    private val networkService: NetworkService
) {

    suspend fun getAllFacts(): Response<FactsResponse> {
        return networkService.getAllFacts()
    }
}