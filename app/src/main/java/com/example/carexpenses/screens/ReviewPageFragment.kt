package com.example.carexpenses.screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carexpenses.R
import com.example.carexpenses.databinding.FragmentHistoryBinding
import com.example.carexpenses.databinding.FragmentReviewPageBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReviewPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentReviewPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




            createPieChart()
            //setBarChart()









    }

    fun createPieChart() {

        val pieChart: PieChart = binding.pieChart
        pieChart.description.isEnabled = false

        pieChart.dragDecelerationFrictionCoef = 0.99f

        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.parseColor("#FCCE5C"))
        pieChart.transparentCircleRadius = 40f
        pieChart.centerText = "Name"
        pieChart.setCenterTextColor(Color.parseColor("#533D2B"))
        pieChart.setCenterTextSize(30f)


        val pieData = mutableListOf<PieEntry>()
        pieData.add(PieEntry(25f))
        pieData.add(PieEntry(75f))
        pieData.add(PieEntry(20f))

        val dataSet = PieDataSet(pieData, "Hello")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.colors = ColorTemplate.JOYFUL_COLORS.toMutableList()

        pieChart.data = PieData(dataSet)
        pieChart.data.setValueTextColor(Color.parseColor("#533D2B"))
        pieChart.data.setValueTextSize(10f)

        pieChart.animateXY(2000,2000)}

    private fun setBarChart() {

        var barChart = binding.barChart

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(8f, 0f))
        entries.add(BarEntry(2f, 1f))
        entries.add(BarEntry(5f, 2f))
        entries.add(BarEntry(20f, 3f))
        entries.add(BarEntry(15f, 4f))
        entries.add(BarEntry(19f, 5f))

        val barDataSet = BarDataSet(entries, "Cells")


        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        //val data = BarData(labels, barDataSet)
        val data = BarData(barDataSet)
        barChart.data = data // set the data and list of lables into chart


        barChart.animateY(5000)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}