package com.laquysoft.kotlinmvpandroid.ui

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.laquysoft.kotlinmvpandroid.R
import com.laquysoft.kotlinmvpandroid.dagger.component.DaggerRatesComponent
import com.laquysoft.kotlinmvpandroid.dagger.module.RatesModule
import com.laquysoft.kotlinmvpandroid.model.RatesAnswer
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by joaobiriba on 23/09/2017.
 */
class RatesListFragment : Fragment(), RatesContract.View {

    @Inject
    lateinit var presenter: RatesContract.Presenter
    private lateinit var mRootView: View

    @BindView(R.id.recyclerview)
    @JvmField
    var recyclerView: RecyclerView? = null

    @BindView(R.id.progressbar)
    @JvmField
    var progressbar: ProgressBar? = null

    fun newInstance(): RatesListFragment {
        return RatesListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attachView(this)
    }

    private fun injectDependency() {
        val ratesComponent = DaggerRatesComponent.builder()
                .ratesModule(RatesModule())
                .build()
        ratesComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mRootView = inflater!!.inflate(R.layout.fragment_list, container, false)
        ButterKnife.bind(this, mRootView)

        initView()

        return mRootView
    }

    fun initView() {
        // call retrofit
        progressbar!!.setVisibility(View.VISIBLE)
        presenter.loadRatesAPI();
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unSubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (!show) {
            progressbar!!.setVisibility(View.GONE)
        }
    }

    override fun showLoadErrorMessage(error: String) {
        showToast(error)
    }

    override fun showEmptyView(visible: Boolean) {
        showToast("No Item")
    }


    override fun onLoadRatesOK(rates: RatesAnswer.Rates) {
        // Here you can get data ans show on UI
        Timber.d("AUD " + rates.AUD)
        Timber.d("EUR " + rates.EUR)
        Timber.d("GBP " + rates.GBP)
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

}