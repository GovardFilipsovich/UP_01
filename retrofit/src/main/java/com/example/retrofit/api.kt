package com.example.retrofit

import retrofit2.http.GET
import retrofit2.Call

interface api{
    @GET("all")
    fun get_posts(): Call<List<Country>>;
}