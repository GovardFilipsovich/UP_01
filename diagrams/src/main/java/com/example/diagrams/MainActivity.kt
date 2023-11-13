package com.example.diagrams

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.LargeValueFormatter
import com.github.mikephil.charting.utils.ValueFormatter


class MainActivity : AppCompatActivity() {

    private var data = arrayListOf<Float>(
        92.6F, 94.02F, 91.73F, 91.73F, 91.73F, 92.66F, 92.10F, 91.85F, 91.68F, 92.18F
    )
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.i("tag", "created")
        setContentView(R.layout.activity_main)
        val chart = findViewById<LineChart>(R.id.chart)
        set_data(chart)

    }

    private fun set_data(chart: LineChart) {
        var entries = arrayListOf<Entry>()
        var i = 0
        var labels = arrayListOf<String>()
        data.forEach{it ->
            entries.add(Entry(it, i))
            labels.add(i.toString())
            i++
        }
        var lineDataSet = LineDataSet(entries, "Курс")

        lineDataSet.lineWidth = 3F
        Log.i("tag", "chart created")
        var lineData = LineData(labels, lineDataSet)
        chart.data = lineData
        chart.setStartAtZero(false)
        chart.setYRange(90F, 95F, true)
        chart.xLabels.textSize = 20F
        chart.yLabels.textSize = 20F
        chart.setValueTextSize(18F)
        chart.legend.textSize = 20F
        Log.i("tag", "Here")
    }

}