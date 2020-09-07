package com.wipro.factsapp.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.wipro.factsapp.mvvmbase.di.components.ApplicationComponent
import com.wipro.factsapp.mvvmbase.di.components.DaggerApplicationComponent
import com.wipro.factsapp.mvvmbase.di.modules.ApplicationModule
import com.wipro.factsapp.mvvmbase.network.NetworkService
import javax.inject.Inject

class FactsApplication : MultiDexApplication(),
    Application.ActivityLifecycleCallbacks {

    var applicationComponent: ApplicationComponent? = null

    @Inject
    lateinit var networkService: NetworkService

    override fun onCreate() {
        super.onCreate()

        // Register Application Life Cycle.
        registerActivityLifecycleCallbacks(this)

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent!!.inject(this)

    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

}