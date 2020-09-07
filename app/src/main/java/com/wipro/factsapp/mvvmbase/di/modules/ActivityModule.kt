package com.wipro.factsapp.mvvmbase.di.modules

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.factsapp.data.dao.repository.FactsRepository
import com.wipro.factsapp.features.facts.viewmodel.FactsViewModel
import com.wipro.factsapp.mvvmbase.base.BaseActivity
import com.wipro.factsapp.mvvmbase.base.ViewModelProviderFactory
import com.wipro.factsapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideFactsViewModel(
        networkHelper: NetworkHelper,
        factsRepository: FactsRepository
    ): FactsViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(FactsViewModel::class) {
            FactsViewModel(networkHelper, factsRepository)
        }).get(FactsViewModel::class.java)

}