package com.wipro.factsapp.features.facts.view

import android.os.Bundle
import com.wipro.factsapp.R
import com.wipro.factsapp.features.facts.viewmodel.FactsViewModel
import com.wipro.factsapp.mvvmbase.base.BaseActivity
import com.wipro.factsapp.mvvmbase.di.components.ActivityComponent

class FactsActivity : BaseActivity<FactsViewModel>() {


    override fun provideLayoutId(): Int = R.layout.activity_facts

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
    }

    override fun setupView(savedInstanceState: Bundle?) {
        val factsFragment = FactsFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fl_homeFragmentContainer, factsFragment)
            .commit()
    }


}