package com.laquysoft.kotlinmvpandroid

import android.app.Application
import com.laquysoft.kotlinmvpandroid.dagger.component.ApplicationComponent
import com.laquysoft.kotlinmvpandroid.dagger.component.DaggerApplicationComponent
import com.laquysoft.kotlinmvpandroid.dagger.module.ApplicationModule
import timber.log.Timber


/**
 * Created by sunil on 5/30/2017.
 */
class MainApplication : Application(){

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setupInjector()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }


    private fun setupInjector() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }


    companion object{
        lateinit var instance : MainApplication private set
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }
}