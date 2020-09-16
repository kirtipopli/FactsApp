package com.wipro.factsapp.features.facts.viewmodel

import com.wipro.factsapp.data.dao.repository.FactsRepository
import com.wipro.factsapp.mvvmbase.base.BaseViewModel
import com.wipro.factsapp.utils.NetworkHelper

class FactsViewModel(
    networkHelper: NetworkHelper,
    private val factsRepository: FactsRepository
) : BaseViewModel(networkHelper) {

    override fun onCreate() {}

}