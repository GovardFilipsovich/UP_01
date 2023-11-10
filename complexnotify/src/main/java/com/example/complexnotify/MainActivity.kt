package com.example.complexnotify

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private lateinit var manager: NotificationManagerCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = resources.getString(R.string.NOT_IMPORTANT_CHANNEL_NAME)
            val chanel = NotificationChannel("NORMAL_CHANEL", name, NotificationManager.IMPORTANCE_LOW)
            chanel.description = resources.getString(R.string.NOT_IMPORTANT_CHANNEL_DESCRIPTION)
            chanel.enableVibration(false)
            manager.createNotificationChannel(chanel)
        }
    }

    fun complexNotification(view: View) {
        var browser = Intent(Intent.ACTION_VIEW)
        browser.setData(Uri.parse("https://bolshoi.ru/"))
        var p_browser = PendingIntent.getActivity(this, R.id.BROWSER_PENDING_ID, browser, PendingIntent.FLAG_UPDATE_CURRENT)
        var map = Intent(Intent.ACTION_VIEW)
        var p_map = PendingIntent.getActivity(this, R.id.MAP_PENDING_ID, map, PendingIntent.FLAG_UPDATE_CURRENT)


        var builder = NotificationCompat.Builder(this, "NORMAL_CHANEL")
        builder.setSmallIcon(android.R.drawable.sym_def_app_icon).setContentTitle("Экскурсия")
            .setContentText("Начинается через 5 минут")

        builder.addAction(NotificationCompat.Action(android.R.drawable.btn_star, "В браузере", p_browser))
        builder.addAction(NotificationCompat.Action(android.R.drawable.btn_star, "На карте", p_map))

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        manager.notify(R.id.BOLSHOI_NOTFICATION_ID, builder.build())
    }
}