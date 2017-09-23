package com.laquysoft.kotlinmvpandroid.dagger.component

import com.laquysoft.kotlinmvpandroid.MainApplication
import com.laquysoft.kotlinmvpandroid.dagger.module.ApplicationModule
import dagger.Component

/**
 * Created by joaobiriba on 23/09/2017.
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: MainApplication)
}