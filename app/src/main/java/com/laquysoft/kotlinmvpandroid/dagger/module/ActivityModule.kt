package com.laquysoft.kotlinmvpandroid.dagger.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * Created by joaobiriba on 23/09/2017.
 */
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivityContext() : Activity {
        return activity
    }
}