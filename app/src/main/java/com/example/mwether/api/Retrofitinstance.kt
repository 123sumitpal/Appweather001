package com.example.mwether.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitinstance {

    private const val baseUrl="http://api.weatherapi.com"

    private fun getInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val wetherApi  : WetherApi = getInstance().create(WetherApi::class.java)
}