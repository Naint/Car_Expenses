package com.example.carexpenses.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.carexpenses.data.Expense
import com.example.carexpenses.databinding.FragmentReviewPageBinding
import com.example.carexpenses.screens.main.ExpenseViewModel
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReviewPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentReviewPageBinding? = null
    private val binding get() = _binding!!

    lateinit var expenseViewModel: ExpenseViewModel

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
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        _binding = FragmentReviewPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




            //createPieChart()
            setBarChart()









    }

    /*fun createPieChart() {

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

        pieChart.animateXY(2000,2000)}*/

    private fun setBarChart() {

        var barChart = binding.barChart

        val entries = ArrayList<BarEntry>()

        expenseViewModel.getAllExpense().observe(this) {listExpense ->

            var sum = 0
            var listCostMonth = arrayListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)


            for (i in listExpense.indices){

               when(listExpense[i].date){

                   "1" -> listCostMonth[0] += listExpense[i].cost
                   "2" -> listCostMonth[1] += listExpense[i].cost
                   "3" -> listCostMonth[2] += listExpense[i].cost
                   "4" -> listCostMonth[3] += listExpense[i].cost
                   "5" -> listCostMonth[4] += listExpense[i].cost
                   "6" -> listCostMonth[5] += listExpense[i].cost
                   "7" -> listCostMonth[6] += listExpense[i].cost
                   "8" -> listCostMonth[7] += listExpense[i].cost
                   "9" -> listCostMonth[8] += listExpense[i].cost
                   "10" -> listCostMonth[9] += listExpense[i].cost
                   "11" -> listCostMonth[10] += listExpense[i].cost
                   "12" -> listCostMonth[11] += listExpense[i].cost
               }

                sum += listExpense.get(i).cost
            }

            for (i in 1..12){
                entries.add(BarEntry(i.toFloat(),listCostMonth[i-1].toFloat()))
            }

            barChart.setBackgroundColor(Color.WHITE)

            val barDataSet = BarDataSet(entries, "")
            barDataSet.color = Color.BLUE

            val data = BarData(barDataSet)
            data.setValueTextColor(Color.WHITE)

            barChart.description.isEnabled = false


            barChart.data = data // set the data and list of lables into chart
            barChart.animateY(1000)



            //Log.i("COST", sum.toString())

        }


/*        for (i in 1..12){
            entries.add(BarEntry(i.toFloat(),5f))
        }

        barChart.setBackgroundColor(Color.WHITE)


        val barDataSet = BarDataSet(entries, "")
        barDataSet.color = Color.BLUE


*//*        val labels = ArrayList<String>()
        labels.add("1")
        labels.add("12n")
        labels.add("3")
        labels.add("4")
        labels.add("5n")
        labels.add("23-Jan")

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels);*//*

        //val data = BarData(labels, barDataSet)
        val data = BarData(barDataSet)
        data.setValueTextColor(Color.WHITE)

        barChart.data = data // set the data and list of lables into chart
        barChart.animateY(1000)*/
    }

    fun getMonth(str : String) : String{
        return str.split(".").toTypedArray()[1]
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