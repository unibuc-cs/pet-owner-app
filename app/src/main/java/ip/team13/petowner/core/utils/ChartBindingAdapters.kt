package ip.team13.petowner.core.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import ip.team13.petowner.R
import ip.team13.petowner.ui.cost.ChartItem


@BindingAdapter("setupWithData")
fun setupChartWithData(pieChart: PieChart, items: List<ChartItem>) {
    pieChart.apply {
        setExtraOffsets(15F, 10F, 15F, 10F)
        animateY(1500, Easing.EaseInOutQuad)
        dragDecelerationFrictionCoef = 0.95f
        elevation = 12f
        description.isEnabled = false
        isDrawHoleEnabled = true
        holeRadius = 40F
        centerText = "Total \n ${items.sumBy { it.value.toInt() }} RON"
    }

    val pieData = PieData(getPieDataSet(pieChart, items))
    pieData.setDrawValues(true)

    pieChart.data = pieData
    pieData.setValueFormatter(object : ValueFormatter() {
        override fun getFormattedValue(value: Float) = "${value.toInt()} RON"
    })
    pieData.setValueTextSize(15f)
    pieData.setValueTextColor(Color.BLACK)
    pieData.setDrawValues(items.isNotEmpty())
    pieChart.highlightValue(null)
    pieChart.setDrawEntryLabels(false)
    pieChart.description.isEnabled = false
    pieChart.legend.apply {
        isEnabled = true
        verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        orientation = Legend.LegendOrientation.HORIZONTAL
        setDrawInside(false)
        xEntrySpace = 7f
        yEntrySpace = 0f
        yOffset = 0f
    }

    pieChart.invalidate()
}

private fun getPieDataSet(pieChart: PieChart, items: List<ChartItem>) =
    when (items.isEmpty()) {
        true -> {
            val dataSet = PieDataSet(arrayListOf(PieEntry(1F, "")), "")
            pieChart.context?.let {
                dataSet.color = ContextCompat.getColor(it, R.color.colorSilver_80)
            }
            dataSet
        }
        false -> {
            val entries = ArrayList<PieEntry>()
            items.forEachIndexed { index, item ->
                entries.add(PieEntry(item.value, item.category))
            }
            PieDataSet(entries, "").apply {
                setDrawIcons(false)
                xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                isValueLineVariableLength = true
                isUsingSliceColorAsValueLineColor = true
                valueLinePart1Length = 0.8f
                valueLinePart2Length = 0.3f
                selectionShift = 6f
                sliceSpace = 2f
                form = Legend.LegendForm.CIRCLE
                colors = getLotOfColors(pieChart.context)
            }
        }
    }

private fun getLotOfColors(context: Context): ArrayList<Int> {
    val colors: ArrayList<Int> = ArrayList()
    colors.add(ContextCompat.getColor(context, R.color.colorAppOrangeBright))
    colors.add(ContextCompat.getColor(context, R.color.colorAppGreen))
    colors.add(ContextCompat.getColor(context, R.color.colorAppYellowBright))
    return colors
}