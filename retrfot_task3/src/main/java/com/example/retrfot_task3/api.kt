package com.example.retrfot_task3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    @GET("{id}")
    fun getCats(@Path("id") id: String): Call<Cat>

    @GET("search?")
    fun getImages(@Query("limit") limit: Int, @Query("has_breeds") has_breeds: Boolean): Call<List<Image>>
}