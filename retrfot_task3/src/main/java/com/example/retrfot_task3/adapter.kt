package com.example.retrfot_task3

import com.example.retrfot_task3.databinding.CatItemBinding

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CatAdapter(private val cats: List<Cat>) :
    RecyclerView.Adapter<CatAdapter.MyViewHolder>() {

    // При помощи биндинга передаем необходимые view в viewHolder
    inner class MyViewHolder(val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Возращаем размер переданного списка пользователей
    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var context = holder.binding.root.context
        Picasso.with(context).load(cats[position].url).into(holder.binding.image)
        var breed = cats[position].breeds[0]
        holder.binding.breed.text = breed.name
        holder.binding.weight.text = breed.weight.metric
        Log.i("tag", "onBindHolder")
    }

}