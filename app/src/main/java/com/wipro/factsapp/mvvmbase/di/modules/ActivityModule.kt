package com.wipro.factsapp.mvvmbase.di.modules

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.factsapp.data.dao.repository.FactsRepository
import com.wipro.factsapp.features.facts.viewmodel.FactsFragmentViewModel
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
    fun provideFactsFragmentViewModel(
        networkHelper: NetworkHelper,
        factsRepository: FactsRepository
    ): FactsFragmentViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(FactsFragmentViewModel::class) {
            FactsFragmentViewModel(networkHelper, factsRepository)
        }).get(FactsFragmentViewModel::class.java)

}