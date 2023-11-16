package com.example.retrfot_task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrfot_task3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var user_api: api
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        var BASE_URL = "https://api.thecatapi.com/v1/images/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        user_api = retrofit.create(api::class.java)

        var cats_list = bind.catsList

        var call = user_api.getImages(10, true)
        var cats: ArrayList<Cat> = arrayListOf()
        var adapter = CatAdapter(cats)
        cats_list.adapter = adapter
        cats_list.layoutManager = LinearLayoutManager(this)
        cats_list.addItemDecoration(Decoration())
        var callback_cat = object : Callback<Cat>{
            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                cats.add(response.body()!!)
                Log.i("tag", response.body().toString())
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Cat>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
        var ids: List<Image> = listOf<Image>()

        call.enqueue(
            object : Callback<List<Image>>{
                override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                    ids = response.body()!!
                    Log.i("tag", ids.toString())
                    ids.forEach{
                        user_api.getCats(it.id).enqueue(callback_cat)
                    }

                }

                override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )



    }
}