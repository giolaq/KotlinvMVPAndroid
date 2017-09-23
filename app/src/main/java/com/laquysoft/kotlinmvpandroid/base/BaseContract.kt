package com.laquysoft.kotlinmvpandroid.base

/**
 * Created by joaobiriba on 23/09/2017.
 */
class BaseContract {

    interface Presenter<in T>{
        fun subscribe()
        fun unSubscribe()
        fun attachView(view : T)
    }

    interface View {

    }
}