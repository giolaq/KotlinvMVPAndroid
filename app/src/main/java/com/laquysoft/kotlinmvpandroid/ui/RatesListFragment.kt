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

        val currencies: List<Float> = listOf(5f, 6f, 1000f);
        val adapter = RatesIndicatorAdapter(activity, currencies.toMutableList(), this);
        val horizontalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewInToolbar!!.setLayoutManager(horizontalLayoutManager)
        recyclerViewInToolbar!!.setAdapter(ScaleInAnimationAdapter(adapter))

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
        val currencies: List<Float> = listOf(rates.AUD!!, rates.EUR!!, rates.GBP!!);
        val adapter = RatesAdapter(activity, currencies.toMutableList(), this);
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView!!.setLayoutManager(horizontalLayoutManagaer)
        recyclerView!!.setAdapter(adapter)
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun handleDummyOperationList() {
        val currencies: List<Float> = listOf(2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 23f, 3f, 5f, 5f);
        val adapter = RatesAdapter(activity, currencies.toMutableList(), this);
        val verticalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewPageLayout!!.setLayoutManager(verticalLayoutManager)
        recyclerViewPageLayout!!.setAdapter(ScaleInAnimationAdapter(adapter))
    }

}