package com.wipro.factsapp.features.facts.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wipro.factsapp.R
import com.wipro.factsapp.features.facts.adapter.FactsListAdapter
import com.wipro.factsapp.features.facts.model.Facts
import com.wipro.factsapp.features.facts.viewmodel.FactsViewModel
import com.wipro.factsapp.mvvmbase.base.BaseActivity
import com.wipro.factsapp.mvvmbase.di.components.ActivityComponent
import kotlinx.android.synthetic.main.activity_facts.*

class FactsActivity : BaseActivity<FactsViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private var factsListAdapter: FactsListAdapter? = null
    private var factsList: ArrayList<Facts>? = null
    private val TAG: String = "FactsResponse"

    override fun provideLayoutId(): Int = R.layout.activity_facts

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isRefreshingLiveData.observe(this, Observer {
            swipeRefresh_Facts.isRefreshing = it
        })

        viewModel.factsResponse
            .observe(this, Observer {
                it?.data?.let { resp ->
                    progress_facts.visibility = View.GONE
                    recycler_facts.visibility = View.VISIBLE
                    tv_emptyFacts.visibility = View.GONE
                    tv_factsTitle.text = resp.title
                    factsListAdapter?.factsList = it.data.rows!!
                    factsListAdapter?.notifyDataSetChanged()
                }
            })

    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.getAllFacts()
        progress_facts.visibility = View.VISIBLE

        factsList = ArrayList()

        swipeRefresh_Facts.setOnRefreshListener(this)
        swipeRefresh_Facts.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorBlue,
            R.color.colorGreen,
            R.color.colorYellow
        )


        factsListAdapter = FactsListAdapter(this, factsList!!, ::onFactsSelected)
        recycler_facts.adapter = factsListAdapter
        recycler_facts.layoutManager = LinearLayoutManager(this)
        factsListAdapter?.notifyDataSetChanged()

        if (factsList.isNullOrEmpty()) {
            tv_emptyFacts.visibility = View.VISIBLE
        }
    }

    private fun onFactsSelected(facts: Facts, position: Int) {
        Toast.makeText(this@FactsActivity, facts.title, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        viewModel.isRefreshingLiveData.postValue(true)
        viewModel.getAllFacts()
    }


}