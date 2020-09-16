package com.wipro.factsapp.features.facts.view

import android.os.Bundle
import com.wipro.factsapp.R
import com.wipro.factsapp.features.facts.viewmodel.FactsFragmentViewModel
import com.wipro.factsapp.mvvmbase.base.BaseActivity
import com.wipro.factsapp.mvvmbase.di.components.ActivityComponent

class FactsActivity : BaseActivity<FactsFragmentViewModel>() {


    override fun provideLayoutId(): Int = R.layout.activity_facts

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
    }

    override fun setupView(savedInstanceState: Bundle?) {
        FactsFragment().let {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.fl_homeFragmentContainer, it)
                .commit()
        }
    }


}