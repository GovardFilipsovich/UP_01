package com.example.calendar

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.time.LocalDate
import java.util.Date

class MainActivity : AppCompatActivity(), CalendarView.OnDateChangeListener {
    private lateinit var manager: NotificationManagerCompat
    private var people = mapOf<String, LocalDate>(
        Pair("Василий Панков", LocalDate.of(2003,11, 5)),
        Pair("Кирилл Гайкин", LocalDate.of(2003,9, 18)),
        Pair("Игорь Осыкин", LocalDate.of(2003,11, 11)),
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cal = findViewById<CalendarView>(R.id.cal)
        cal.setOnDateChangeListener(this)
        manager = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = resources.getString(R.string.NOT_IMPORTANT_CHANNEL_NAME)
            val chanel = NotificationChannel("NORMAL_CHANEL", name, NotificationManager.IMPORTANCE_LOW)
            chanel.description = resources.getString(R.string.NOT_IMPORTANT_CHANNEL_DESCRIPTION)
            chanel.enableVibration(false)
            manager.createNotificationChannel(chanel)
        }
    }

    override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {



            people.forEach{ human ->
                var date = human.value
                var name = human.key
                if (date.month.ordinal == p2  && date.dayOfMonth == p3){
                    var builder = NotificationCompat.Builder(this, "NORMAL_CHANEL")
                    builder.setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setContentTitle("Не забудьте поздравить!!!")
                        .setContentText("Сегодня день рождение у " + name)
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) { return }

                    manager.notify(R.id.BOLSHOI_NOTFICATION_ID, builder.build())
            }

        }
    }


}