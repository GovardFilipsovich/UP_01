package com.example.employers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.employers.databinding.ActivityMainBinding
import java.net.URL
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        var source = arrayListOf<EmployerModel>(EmployerModel(empName = "123"))
        // Адаптер для recyclerlist
        var adapter: EmployerAdapter
        // Сам список
        val emp_list = bind.empList

        val commands = arrayListOf<String>("Команда верстальщиков", "Команда бэкэндеров", "Звери", "Кароси", "Мега-кароси", "Все подряд", "Тут могла быть ваша реклама")
        thread {
            val json = try {
                URL("https://api.randomdatatools.ru/?params=LastName,FirstName,FatherName,Email&unescaped=false&count=30").readText()
            } catch (e: Exception) {
                Log.i("tag", e.toString());return@thread
            }
            var parser = Parser.default()
            val stringBuilder: StringBuilder = StringBuilder(json)
            val jsonObj: JsonArray<*> = parser.parse(stringBuilder) as JsonArray<*>

            source = jsonObj.map{
                var j: JsonObject = it as JsonObject
                EmployerModel(j["FirstName"].toString(), j["LastName"].toString(), j["FatherName"].toString(),
                    avatarURL="https://api.multiavatar.com/${Random.nextInt()}.png",
                    commandName = commands[Random.nextInt(0, commands.size)],
                    numberOfProjects = Random.nextInt(1, 5),
                    lines = Random.nextInt(0, 300) * 100,
                    rate = Random.nextInt(3, 11),
                    phone = "+${Random.nextInt(0, 10)} ${Random.nextInt(100, 999)} ${Random.nextInt(1000, 9999)}",
                    mail = j["Email"].toString()
                )
            }.toList() as ArrayList<EmployerModel>

            runOnUiThread {
                try {
                    // У адаптера есть встроенная возможность обновления данных
                    adapter = EmployerAdapter(source)
                    emp_list.layoutManager = LinearLayoutManager(this)
                    emp_list.adapter = adapter
                    emp_list.addItemDecoration(Decoration())
                }
                catch (e : Exception){
                    Log.i("tag", e.toString())
                }
            }
        }
    }
}