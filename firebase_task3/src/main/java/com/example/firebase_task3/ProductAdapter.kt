package com.example.firebase_task3

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_task3.databinding.OrderItemBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    // При помощи биндинга передаем необходимые view в viewHolder
    inner class MyViewHolder(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Возращаем размер переданного списка пользователей
    override fun getItemCount() = products.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var context = holder.binding.root.context
        Picasso.with(context).load(products[position].Pic_url).into(holder.binding.imageProduct)
        Log.e("tag", products[position].Pic_url)
        holder.binding.cost.text = products[position].Cost.toString()
        holder.binding.name.text = products[position].Name

        holder.binding.isChoice.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            products[position].is_select = b
            if(b){
                products[position].count = 1
            }else{
                products[position].count = null
                holder.binding.cost.text = ""
            }

        }

        holder.binding.count.addTextChangedListener {
            products[position].count = it.toString().toInt()
        }


    }

}