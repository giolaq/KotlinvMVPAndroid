package com.laquysoft.kotlinmvpandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by joaobiriba on 23/09/2017.
 */
class RatesAnswer {

      @SerializedName("rates")
      @Expose
      var rates: Rates? = null

      class Rates {

            @SerializedName("GBP")
            @Expose
            var GBP: Float? = null
            @SerializedName("EUR")
            @Expose
            var EUR: Float? = null
            @SerializedName("AUD")
            @Expose
            var AUD: Float? = null

            constructor(GBP: Float?, EUR: Float?, AUD: Float?) {
                  this.GBP = GBP
                  this.EUR = EUR
                  this.AUD = AUD
            }
      }

}