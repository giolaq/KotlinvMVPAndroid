package com.laquysoft.kotlinmvpandroid.dagger.component

import com.laquysoft.kotlinmvpandroid.MainActivity
import com.laquysoft.kotlinmvpandroid.dagger.module.ActivityModule
import com.laquysoft.kotlinmvpandroid.dagger.scope.PerActivity
import dagger.Component

/**
 * Created by joaobiriba on 23/09/2017.
 */
@PerActivity
@Component( modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)

}