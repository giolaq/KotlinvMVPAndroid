package com.laquysoft.kotlinmvpandroid.ui

import com.laquysoft.kotlinmvpandroid.base.BaseContract
import com.laquysoft.kotlinmvpandroid.model.RatesAnswer

/**
 * Created by joaobiriba on 23/09/2017.
 */
class RatesContract {

    interface Presenter : BaseContract.Presenter<View>{
        fun loadRatesAPI()
    }

    interface View : BaseContract.View{
        fun showProgress(show : Boolean)
        fun showLoadErrorMessage(error : String)
        fun showEmptyView(visible : Boolean)
        fun onLoadRatesOK(rates : RatesAnswer.Rates)
    }
}