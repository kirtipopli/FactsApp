package com.wipro.factsapp.mvvmbase.di.components

import android.app.Application
import android.content.Context
import com.wipro.factsapp.application.FactsApplication
import com.wipro.factsapp.mvvmbase.di.ApplicationContext
import com.wipro.factsapp.mvvmbase.di.modules.ApplicationModule
import com.wipro.factsapp.mvvmbase.network.NetworkService
import com.wipro.factsapp.mvvmbase.rx.SchedulerProvider
import com.wipro.factsapp.utils.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: FactsApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

}