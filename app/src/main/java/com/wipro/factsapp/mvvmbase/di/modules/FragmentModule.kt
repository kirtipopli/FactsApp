package com.wipro.factsapp.mvvmbase.di.modules

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.factsapp.data.dao.repository.FactsRepository
import com.wipro.factsapp.features.facts.viewmodel.FactsFragmentViewModel
import com.wipro.factsapp.mvvmbase.base.BaseFragment
import com.wipro.factsapp.mvvmbase.base.ViewModelProviderFactory
import com.wipro.factsapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideFactsFragmentViewModel(
        networkHelper: NetworkHelper, ezHomeRepository: FactsRepository
    ): FactsFragmentViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(FactsFragmentViewModel::class) {
            FactsFragmentViewModel(
                networkHelper,
                ezHomeRepository
            )
        }).get(FactsFragmentViewModel::class.java)

}