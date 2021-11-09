package com.nsolution.nfooddriver.Adapter.Chart

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.nsolution.nfooddriver.Models.ItemEarningChartDto
import com.nsolution.nfooddriver.R

class EarningsChart {

  constructor(chart : BarChart,data : ArrayList<Float>?) {
    val entries = arrayListOf<BarEntry>()

    data?.let {
      for (index in 0..data.size - 1) {
        entries.add(BarEntry(index.toFloat(), data.get(index)))
      }
    }

    setUpChart(chart)

    val dataSet = BarDataSet(entries,"")
    dataSet.color = Color.parseColor("#7ac142")

    chart.data = BarData(dataSet)
    chart.animateY(1000)
  }

  fun setUpChart(chart : BarChart){
    chart.axisLeft.apply {
      setDrawLabels(false)
      setDrawAxisLine(false)
      setDrawGridLines(false)
      setDrawZeroLine(false)
    }
    chart.axisRight.apply {
      setDrawLabels(false)
      setDrawAxisLine(false)
      setDrawGridLines(false)
      setDrawZeroLine(false)
    }
    chart.xAxis.apply {
      setDrawGridLines(false)
      disableGridDashedLine()
      setDrawAxisLine(false)
      setDrawLabels(false)
    }
  }
}