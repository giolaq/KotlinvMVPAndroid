package com.laquysoft.kotlinmvpandroid.dagger.module

import com.laquysoft.kotlinmvpandroid.ui.RatesContract
import com.laquysoft.kotlinmvpandroid.ui.RatesPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by joaobiriba on 23/09/2017.
 */
@Module
class RatesModule {

     @Provides
     fun getRatesPresenter() : RatesContract.Presenter{
      return RatesPresenter()
    }
}