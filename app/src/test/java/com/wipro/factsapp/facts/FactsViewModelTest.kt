package com.wipro.factsapp.facts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.wipro.factsapp.data.dao.repository.FactsRepository
import com.wipro.factsapp.data.network.callback.Resource
import com.wipro.factsapp.features.facts.model.FactsResponse
import com.wipro.factsapp.features.facts.viewmodel.FactsFragmentViewModel
import com.wipro.factsapp.utils.NetworkHelper
import io.reactivex.schedulers.TestScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var factsRepository: FactsRepository

    @Mock
    private lateinit var factsObserver: Observer<Resource<FactsResponse>>

    private lateinit var factsViewModel: FactsFragmentViewModel

    private lateinit var testScheduler: TestScheduler


    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        factsViewModel = FactsFragmentViewModel(
            networkHelper,
            factsRepository
        )
        factsViewModel.factsResponse.observeForever(factsObserver)
    }


    @Test
    fun getAllFacts() {
        factsViewModel.getAllFacts()
        runBlocking {
            (factsViewModel.viewModelScope.coroutineContext[Job]?.children?.forEach { it.join() })
        }
    }

    @Test
    fun isInternetConnected() {
        factsViewModel.isInternetConnected()
    }

    @After
    fun tearDown() {
        factsViewModel.factsResponse.removeObserver(factsObserver)
    }


}