package com.wipro.factsapp.features.facts.view

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wipro.factsapp.R
import com.wipro.factsapp.features.facts.adapter.FactsListAdapter
import com.wipro.factsapp.features.facts.model.Facts
import com.wipro.factsapp.features.facts.viewmodel.FactsFragmentViewModel
import com.wipro.factsapp.mvvmbase.base.BaseFragment
import com.wipro.factsapp.mvvmbase.di.components.FragmentComponent
import kotlinx.android.synthetic.main.fragment_facts.*

class FactsFragment : BaseFragment<FactsFragmentViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private var factsListAdapter: FactsListAdapter? = null
    private var factsList: ArrayList<Facts>? = null

    override fun provideLayoutId(): Int = R.layout.fragment_facts

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isInternetConnected.observe(this, Observer {
            if (it) {
                if (snackBarAlreadyVisible()!!) showSnackBarMessage(
                    swipeRefresh_Facts,
                    getString(R.string.internet_connected),
                    true
                )
            } else {
                progress_facts.visibility = View.GONE

                if (snackbar != null) snackbar?.dismiss()
                showSnackBarMessage(
                    swipeRefresh_Facts,
                    getString(R.string.no_internet_connection),
                    false
                )
            }

        })

        viewModel.isRefreshingLiveData.observe(this, Observer {
            swipeRefresh_Facts.isRefreshing = it
        })

        viewModel.factsResponse
            .observe(this, Observer {
                it?.data?.let { resp ->
                    progress_facts.visibility = View.GONE
                    recycler_facts.visibility = View.VISIBLE
                    tv_emptyFacts.visibility = View.GONE
                    (activity as AppCompatActivity).supportActionBar?.title = resp.title
//                    tv_factsTitle.text = resp.title
                    factsListAdapter?.factsList = it.data.rows!!
                    factsListAdapter?.notifyDataSetChanged()
                }
            })
    }

    override fun setupView(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

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


        factsListAdapter = context?.let { FactsListAdapter(it, factsList!!, ::onFactsSelected) }
        recycler_facts.adapter = factsListAdapter
        recycler_facts.layoutManager = LinearLayoutManager(context)
        factsListAdapter?.notifyDataSetChanged()

        if (factsList.isNullOrEmpty()) {
            tv_emptyFacts.visibility = View.VISIBLE
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFacts()
    }

    private fun onFactsSelected(facts: Facts, position: Int) {
        Toast.makeText(activity, facts.title, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        viewModel.isRefreshingLiveData.postValue(true)
        viewModel.getAllFacts()
    }

/*
    private fun showNoNetSnackbar() {
        val snack = Snackbar.make(rootView, "No Internet!", Snackbar.LENGTH_LONG) // replace root view with your view Id
        snack.setAction("Settings") {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
        snack.show()
    }
*/

}