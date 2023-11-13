package com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var user_api: api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val BASE_URL = "https://restcountries.eu/rest/v2/"
        Log.i("tag", BASE_URL)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.i("tag", "retrofit build")
        user_api = retrofit.create(api::class.java)
        var textApi = findViewById<TextView>(R.id.data)
        Log.i("tag", "before call")
        var call = user_api.get_posts()
        call.enqueue(
            object : Callback<List<Country>>{
                override fun onResponse(
                    call: Call<List<Country>>,
                    response: Response<List<Country>>
                ) {
                    Log.i("tag", "onResponse")
                    response.body()?.forEach {
                        Log.i("tag", it.toString())
                        var content = "Страна:"
                        content += it.nameCountry + " "
                        content += "Население " + it.populationCountry
                        textApi.append(content)
                    }
                }

                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    Log.e("tag", "Fail " + t.toString())
                }

            }
        )


    }
}