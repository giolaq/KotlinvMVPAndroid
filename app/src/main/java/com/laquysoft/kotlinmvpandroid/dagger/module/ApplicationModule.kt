package com.laquysoft.kotlinmvpandroid.dagger.module

import android.app.Application
import com.laquysoft.kotlinmvpandroid.MainApplication
import com.laquysoft.kotlinmvpandroid.dagger.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by joaobiriba on 23/09/2017.
 */
@Module
class ApplicationModule(private val mainApplication: MainApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplicationContext(): Application {
        return mainApplication
    }
}