package com.example.myapplication

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
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

    fun simpleNotification(view: View) : Unit{
        var builder = NotificationCompat.Builder(this, "NORMAL_CHANEL")
        builder.setSmallIcon(android.R.drawable.btn_star).setContentTitle("Простое оповещение")
            .setContentText("Очень очень важное")
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.beachpicture))


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        manager.notify(R.id.SIMPLE_NOTIFICATION_ID, builder.build())
    }

    fun simpleCancel(view: View) {
        manager.cancel(R.id.SIMPLE_NOTIFICATION_ID)
    }

    fun browserLaunch(view: View) {
        var a2 = Intent(this, browser::class.java)
        var pa2 = PendingIntent.getActivity(this, R.id.BROWSER_PENDING_ID, a2, PendingIntent.FLAG_UPDATE_CURRENT)
        var builder = NotificationCompat.Builder(this, "NORMAL_CHANEL")
        builder.setSmallIcon(android.R.drawable.btn_star).setContentTitle("Запустить браузер")
               .setContentText("Посмотреть google.com")
               .setContentIntent(pa2)
               .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        manager.notify(R.id.GOOGLE_NOTIFICATION_ID, builder.build())
    }
}