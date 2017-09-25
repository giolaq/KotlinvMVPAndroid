package com.laquysoft.kotlinmvpandroid.ui

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
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
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.jetbrains.anko.AnkoLogger
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
    var recyclerView: SnappyRecyclerView? = null

    @BindView(R.id.recycler_view_pagelayout)
    @JvmField
    var recyclerViewPageLayout: RecyclerView? = null

    @BindView(R.id.recyclerview_in_toolbar)
    @JvmField
    var recyclerViewInToolbar: SnappyRecyclerView? = null

    @BindView(R.id.my_toolbar)
    @JvmField
    var toolbar: Toolbar? = null

    @BindView(R.id.app_bar_layout)
    @JvmField
    var appBarLayout: AppBarLayout? = null

    @BindView(R.id.progressbar)
    @JvmField
    var progressbar: ProgressBar? = null

    @BindView(R.id.bottom_nav)
    @JvmField
    var bottomNavigation: BottomNavigationView? = null


    private lateinit var ratesAdapter: RatesAdapter
    private lateinit var indicatorAdapter: RatesIndicatorAdapter

    private var ratesList: MutableList<String> =  mutableListOf("£","€","$")

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

        val supportActivity = activity as AppCompatActivity
        supportActivity.setSupportActionBar(toolbar)

        // call retrofit
        progressbar!!.setVisibility(View.VISIBLE)
        presenter.loadRatesAPI()


        handleDummyOperationList()

        indicatorAdapter = RatesIndicatorAdapter(activity, ratesList, this);
        recyclerViewInToolbar!!.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false))
        recyclerViewInToolbar!!.setAdapter(ScaleInAnimationAdapter(indicatorAdapter))

        val params = appBarLayout!!.getLayoutParams() as CoordinatorLayout.LayoutParams
        params.behavior = AppBarLayout.Behavior()
        val behavior = params.behavior as AppBarLayout.Behavior?
        behavior!!.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })

        val bottomNavBarLayoutParams = bottomNavigation!!.getLayoutParams() as CoordinatorLayout.LayoutParams
        bottomNavBarLayoutParams.setBehavior(BottomNavigationViewBehavior());

        ratesAdapter = RatesAdapter(activity, ratesList, this);
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false))
        recyclerView!!.setAdapter(ratesAdapter)
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
        Timber.d("GBP " + rates.GBP)
        Timber.d("EUR " + rates.EUR)
        ratesList = mutableListOf(activity.getString(R.string.gbp, rates.GBP),
                activity.getString(R.string.eur, rates.EUR),
                activity.getString(R.string.usd, 1f))
        ratesAdapter.clearAndAddElement(ratesList)
        indicatorAdapter.clearAndAddElement(ratesList)
    }


    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun handleDummyOperationList() {
        val transactions: List<String> = listOf("Transaction 1"
                , "Transaction 2"
                , "Transaction 3"
                , "Transaction 4"
                , "Transaction 5"
                , "Transaction 6"
                , "Transaction 7"
                , "Transaction 8"
                , "Transaction 9"
                , "Transaction 10"
                , "Transaction 11"
                , "Transaction 12"
                , "Transaction 13"
                , "Transaction 14"
                , "Transaction 15"
                , "Transaction $$$"
        );
        val adapter = TransactionAdapter(activity, transactions.toMutableList(), this);
        val verticalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewPageLayout!!.setLayoutManager(verticalLayoutManager)
        recyclerViewPageLayout!!.setAdapter(ScaleInAnimationAdapter(adapter))
    }

}