package com.example.mwether.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WetherApi {

    @GET("/v1/current.json")
    suspend fun getWether(
        @Query("key") apikey :String,
        @Query("q") city:String

    ) : Response<WeatherModel>
}