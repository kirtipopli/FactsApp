package com.wipro.factsapp.mvvmbase.di.components

import com.wipro.factsapp.features.facts.view.FactsActivity
import com.wipro.factsapp.mvvmbase.di.ActivityScope
import com.wipro.factsapp.mvvmbase.di.modules.ActivityModule
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(factsActivity: FactsActivity)
}