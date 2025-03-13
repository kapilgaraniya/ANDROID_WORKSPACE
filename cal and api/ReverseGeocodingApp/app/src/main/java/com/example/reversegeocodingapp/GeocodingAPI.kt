package com.example.reversegeocodingapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingAPI {
    @GET("reverse")
    fun getAddress(
        @Query("format") format: String = "json",
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("zoom") zoom: Int = 18,
        @Query("addressdetails") addressDetails: Int = 1
    ): Call<GeocodingResponse>
}
