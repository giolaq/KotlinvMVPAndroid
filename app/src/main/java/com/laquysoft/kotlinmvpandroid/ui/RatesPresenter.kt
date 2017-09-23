package com.laquysoft.kotlinmvpandroid.ui

import com.laquysoft.kotlinmvpandroid.api.ApiService
import com.laquysoft.kotlinmvpandroid.model.RatesAnswer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by joaobiriba on 23/09/2017.
 */
class RatesPresenter : RatesContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private lateinit var view: RatesContract.View

    override fun loadRatesAPI() {
        getRates(true)
    }

    override fun attachView(view: RatesContract.View) {
        this.view = view
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
        subscriptions.clear()
    }

    fun getRates(isSubcribes : Boolean){
       if (isSubcribes) {
           var observableRates = ApiService().loadRates()
           updateView(observableRates, true)
       }
    }

    fun updateView(observableRates: Observable<RatesAnswer>, isAPI : Boolean){
       var subscribe =  observableRates.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
               .subscribe({answer: RatesAnswer? ->
                   if (isAPI){
                       // insert into db
                       var listUsers = answer!!.rates;
                       view.onLoadRatesOK(listUsers!!)
                       view.showProgress(false)}},
                       { t: Throwable? -> view.showEmptyView(true)
                           view.showProgress(false)})
                       subscriptions.add(subscribe)
        }


}