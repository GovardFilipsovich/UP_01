package com.example.employers

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView


class EmployerAdapter(private val users: List<EmployerModel>) :
    RecyclerView.Adapter<EmployerAdapter.MyViewHolder>() {

    // При помощи биндинга передаем необходимые view в viewHolder
    inner class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    // Возращаем размер переданного списка пользователей
    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var cur = users[position]
        holder.binding.fullName.text = "${cur.empSurname} ${cur.empName} ${cur.empPatronymic}"
        val context = holder.binding.root.context
        val ava = holder.binding.avatar
        //ava.setColorFilter(Color.RED)
        // Заполняем с помощью пикассо картинку
        Picasso
            // Где рисуем
            .with(context)
            // Откуда берем картинку
            .load(cur.avatarURL)
            // В какой виджет рисуем
            .into(ava,
                object: com.squareup.picasso.Callback{
                    // Что делаем, если успешно загрузили картинку
                    override fun onSuccess(){
                        // Устанавливаем background (через xml не получилось - отрисовка картинки затирает background)
                        //DrawableCompat.setTint(ava.drawable, Color.RED);
                    }

                    override fun onError() {
                    }
                }
            )

        // Заполнение команды
        holder.binding.commandName.text = "Команда: " + cur.commandName

        // Заполнение рейтинга
        holder.binding.rateView.text = cur.rate.toString()

        // Заполнение кол-ва проектов
        holder.binding.projectsView.text = cur.numberOfProjects.toString()

        // Заполнение строк кода
        holder.binding.linesView.text = cur.lines.toString()

        // Переход на активити с доп. информацией
        holder.binding.viewButton.setOnClickListener{
            Log.i("tag", cur.toString())
            val intent = Intent(context, info::class.java)
            intent.putExtra("curJson", Klaxon().toJsonString(cur))
            context.startActivity(intent)

        }
    }

}