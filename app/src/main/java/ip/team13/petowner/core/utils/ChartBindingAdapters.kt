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

    pieChart.data = pieData
    pieData.setValueFormatter(object : ValueFormatter() {
        override fun getFormattedValue(value: Float) = "$${value.toInt()}"
    })
    pieData.setValueTextSize(15f)
    pieData.setValueTextColor(Color.BLACK)
    pieData.setDrawValues(values.isNotEmpty())
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
                colors = getLotOfColors()
            }
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