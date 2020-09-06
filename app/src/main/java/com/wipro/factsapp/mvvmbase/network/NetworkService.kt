package com.wipro.factsapp.mvvmbase.network

import com.wipro.factsapp.features.facts.model.FactsResponse
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    /**GET All Facts*/

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getAllFacts(): Response<FactsResponse>

}