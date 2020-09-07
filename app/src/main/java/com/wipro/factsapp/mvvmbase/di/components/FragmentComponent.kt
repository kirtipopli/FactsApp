package com.wipro.factsapp.mvvmbase.di.components

import com.wipro.factsapp.features.facts.view.FactsFragment
import com.wipro.factsapp.mvvmbase.di.FragmentScope
import com.wipro.factsapp.mvvmbase.di.modules.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(factsFragment: FactsFragment)
}