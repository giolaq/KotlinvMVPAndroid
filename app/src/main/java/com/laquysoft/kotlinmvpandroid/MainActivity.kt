package com.laquysoft.kotlinmvpandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.laquysoft.kotlinmvpandroid.dagger.component.DaggerActivityComponent
import com.laquysoft.kotlinmvpandroid.ui.RatesListFragment
import com.laquysoft.kotlinmvpandroid.util.ActivityUtil
import com.laquysoft.kotlinmvpandroid.dagger.module.ActivityModule

/*
 * Created by joaobiriba on 23/09/2017.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        ActivityUtil().addFragmentToActivity(fragmentManager, RatesListFragment().newInstance(), R.id.frame, "RatesListFragment")
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
