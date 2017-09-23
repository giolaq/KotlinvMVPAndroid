package com.laquysoft.kotlinmvpandroid.api

import com.laquysoft.kotlinmvpandroid.model.RatesAnswer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by joaobiriba on 23/09/2017.
 */
interface ApiServiceInterface {

    @GET("latest")
    fun getRates(@Query("base") base: String): Observable<RatesAnswer>

}