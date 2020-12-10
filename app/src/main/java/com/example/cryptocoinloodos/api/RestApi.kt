package com.example.cryptocoinloodos.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("coins/list")
    fun getCryptoCoin(): Observable<ResponseBody>

    @GET("coins/{id}")
    fun getCryptoCoinDetail(
        @Path("id") id: String
    ): Observable<ResponseBody>

}