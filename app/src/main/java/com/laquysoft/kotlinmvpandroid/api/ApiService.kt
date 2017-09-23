package com.laquysoft.kotlinmvpandroid.api

import com.google.gson.GsonBuilder
import com.laquysoft.kotlinmvpandroid.model.RatesAnswer
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by joaobiriba on 23/09/2017.
 */
class ApiService {

    val service : ApiServiceInterface
    var baseUrl = "http://api.fixer.io/"

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<ApiServiceInterface>(ApiServiceInterface::class.java)
    }

    fun loadRates(): Observable<RatesAnswer> {
        return service.getRates("USD")
    }
}

