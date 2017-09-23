package com.laquysoft.kotlinmvpandroid.util

import android.app.Fragment
import android.app.FragmentManager
/**
 * Created by joaobiriba on 23/09/2017.
 */
class ActivityUtil {

    public fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int, fragmentTag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, fragmentTag).addToBackStack("")
        transaction.commit()
    }
}