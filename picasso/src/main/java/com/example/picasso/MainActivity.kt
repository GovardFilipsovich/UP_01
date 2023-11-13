package com.example.picasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    var urls = arrayListOf<String>(
        "https://sun9-3.userapi.com/c639719/u23899114/video/y_b71e9e5f.jpg",
        "https://wallbox.ru/resize/640x480/wallpapers/main/201516/e166111b01fab62.jpg",
        "https://wallbox.ru/resize/640x480/wallpapers/main/201318/403ca1342b686f3.jpg",
        "https://img3.goodfon.ru/original/640x480/e/b6/gory-les-sneg-voda.jpg",
        "https://sun9-6.userapi.com/c604425/u164669008/video/y_02b1d57f.jpg",
        "https://img.goodfon.ru/original/640x480/a/80/nebo-trava-priroda.jpg",
        "https://wallbox.ru/resize/640x480/wallpapers/main/201136/ozero-gory-el-54e99a2.jpg",
        "https://wallbox.ru/resize/640x480/wallpapers/main/201505/dbc10cfe6cced34.jpg",
        "https://avatars.mds.yandex.net/get-pdb/936467/71dd471b-ab31-4580-96c0-f2cc73a3ab46/s1200?webp=false"
    )

    var iters = urls.iterator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var im = findViewById<ImageView>(R.id.imageView)
        Picasso
            .get()
            .load(urls[0])
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .resize(320,240)
            .into(im)
        var but = findViewById<Button>(R.id.but)

        but.setOnClickListener {
            if (!iters.hasNext()){
                iters = urls.iterator()
            }
            Picasso
                .get()
                .load(iters.next())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .resize(320,240)
                .into(im)
        }


    }
}