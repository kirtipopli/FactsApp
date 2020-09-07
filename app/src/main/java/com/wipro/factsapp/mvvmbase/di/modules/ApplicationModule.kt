package com.wipro.factsapp.mvvmbase.di.modules

import android.app.Application
import android.content.Context
import com.wipro.factsapp.BuildConfig
import com.wipro.factsapp.application.FactsApplication
import com.wipro.factsapp.mvvmbase.di.ApplicationContext
import com.wipro.factsapp.mvvmbase.network.NetworkService
import com.wipro.factsapp.mvvmbase.network.Networking
import com.wipro.factsapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: FactsApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "",
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024,//10mb
            application
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}