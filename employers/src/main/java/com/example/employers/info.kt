package com.example.employers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.employers.databinding.ActivityInfoBinding

import java.lang.StringBuilder

class info : AppCompatActivity() {

    private lateinit var bind: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_info)

        // Получение и десериализация объекта
        var parser = Parser.default()
        var employer = intent.getStringExtra("curJson")?.let { Klaxon().parse<EmployerModel>(it) }

        bind.viewmodel = employer
        bind.lifecycleOwner = this

        if (employer != null) {
            Picasso
                // Где рисуем
                .with(this)
                // Откуда берем картинку
                .load(employer?.avatarURL)
                // В какой виджет рисуем
                .into(bind.avatar)
        }




    }
}