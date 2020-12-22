package ip.team13.petowner.core.utils

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
import com.github.mikephil.charting.utils.ColorTemplate
import ip.team13.petowner.R
import java.text.DecimalFormat


@BindingAdapter("setupWithData")
fun setupChartWithData(pieChart: PieChart, values: ArrayList<Float>) {
    pieChart.apply {
        setExtraOffsets(15F, 10F, 15F, 10F)
        animateY(1500, Easing.EaseInOutQuad)
        dragDecelerationFrictionCoef = 0.95f
        elevation = 12f
        description.isEnabled = false
        isDrawHoleEnabled = false
    }

    val pieData = PieData(getPieDataSet(pieChart, values))
    pieData.setDrawValues(true)
    val largeValueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return getFormatValue(value)
        }
    }

    pieChart.data = pieData
    pieData.setValueFormatter(largeValueFormatter)
    pieData.setValueTextSize(15f)
    pieData.setValueTextColor(Color.BLACK)
    pieData.setDrawValues(values.isNotEmpty())
    pieChart.highlightValue(null)
    pieChart.setDrawEntryLabels(false)
    pieChart.description.isEnabled = false
    pieChart.legend.isEnabled = true
    pieChart.legend.apply {
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

private fun getPieDataSet(pieChart: PieChart, values: ArrayList<Float>) =
    when (values.isEmpty()) {
        true -> {
            val dataSet = PieDataSet(arrayListOf(PieEntry(1F, "")), "")
            pieChart.context?.let {
                dataSet.color = ContextCompat.getColor(it, R.color.colorSilver_80)
            }
            dataSet
        }
        false -> {
            val entries = ArrayList<PieEntry>()
            values.forEachIndexed { index, value ->
                entries.add(PieEntry(value, "label $index"))
            }
            val dataSet = PieDataSet(entries, "")
            dataSet.setDrawIcons(false)
            dataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
            dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
            dataSet.isValueLineVariableLength = true
            dataSet.isUsingSliceColorAsValueLineColor = true
            dataSet.valueLinePart1Length = 0.8f
            dataSet.valueLinePart2Length = 0.3f
            dataSet.selectionShift = 6f
            dataSet.sliceSpace = 2f
            dataSet.form = Legend.LegendForm.CIRCLE
            dataSet.colors = getLotOfColors()
            dataSet
        }
    }

private fun getLotOfColors(): ArrayList<Int> {
    val colors: ArrayList<Int> = ArrayList()
    colors.addAll(ColorTemplate.VORDIPLOM_COLORS.toList())
    colors.addAll(ColorTemplate.JOYFUL_COLORS.toList())
    colors.addAll(ColorTemplate.COLORFUL_COLORS.toList())
    colors.addAll(ColorTemplate.LIBERTY_COLORS.toList())
    colors.addAll(ColorTemplate.PASTEL_COLORS.toList())
    return colors
}


private fun getFormatValue(value: Float): String {
    var formattedValue = value
    val pattern = when {
        value >= 10000 && value < 1000000 -> {
            formattedValue = formattedValue.div(1000)
            "#.##K"
        }
        value >= 1000000 -> {
            formattedValue = formattedValue.div(1000000)
            "#.##M"
        }
        else -> "#,###"
    }
    return "$${DecimalFormat(pattern).format(formattedValue)}"
}