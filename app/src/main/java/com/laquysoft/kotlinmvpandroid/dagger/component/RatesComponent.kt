package com.laquysoft.kotlinmvpandroid.dagger.component

import com.laquysoft.kotlinmvpandroid.dagger.module.RatesModule
import com.laquysoft.kotlinmvpandroid.dagger.scope.PerActivity
import com.laquysoft.kotlinmvpandroid.ui.RatesListFragment
import dagger.Component

/**
 * Created by joaobiriba on 23/09/2017.
 */
@PerActivity
@Component( modules = arrayOf(RatesModule::class))
interface RatesComponent {
    fun inject(ratesListFragment: RatesListFragment)
}