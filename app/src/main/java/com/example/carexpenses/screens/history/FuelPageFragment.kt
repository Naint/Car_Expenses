package com.example.carexpenses.screens.history

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.carexpenses.databinding.FragmentFuelPageBinding
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FuelPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentFuelPageBinding? = null
    private val binding get() = _binding!!

    lateinit var fuelViewModel: FuelViewModel

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

        fuelViewModel = ViewModelProvider(this)[FuelViewModel::class.java]

        _binding = FragmentFuelPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBarChart()
        setLiterChart()
        getFuels()
    }

    private fun setBarChart() {

        //https://russianblogs.com/article/2984929939/

        val barChart = binding.barChart
        val xAxis = barChart.xAxis
        val axisRightY: YAxis = barChart.axisRight
        val axisLeftY: YAxis = barChart.axisLeft
        val currentDate = SimpleDateFormat("M").format(Date())

        val entries = ArrayList<BarEntry>()






        fuelViewModel.getAllFuel().observe(this) {listExpense ->

            var sum = 0.0
            var listCostMonth = arrayListOf<Double>(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)


            for (i in listExpense.indices){

                when(getMonth(listExpense[i].date)){

                    "01" -> listCostMonth[0] += listExpense[i].price
                    "02" -> listCostMonth[1] += listExpense[i].price
                    "03" -> listCostMonth[2] += listExpense[i].price
                    "04" -> listCostMonth[3] += listExpense[i].price
                    "05" -> listCostMonth[4] += listExpense[i].price
                    "06" -> listCostMonth[5] += listExpense[i].price
                    "07" -> listCostMonth[6] += listExpense[i].price
                    "08" -> listCostMonth[7] += listExpense[i].price
                    "09" -> listCostMonth[8] += listExpense[i].price
                    "10" -> listCostMonth[9] += listExpense[i].price
                    "11" -> listCostMonth[10] += listExpense[i].price
                    "12" -> listCostMonth[11] += listExpense[i].price
                }

                //sum += listExpense[i].cost

                //binding.monthCost.text = "${listCostMonth[currentDate.toInt()-1]}₽"
            }

            for (i in 1..12){
                entries.add(BarEntry(i.toFloat(), listCostMonth[i-1].toFloat()))
            }

            barChart.setBackgroundColor(Color.parseColor("#E4D8FF"))
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(false)
            xAxis.setCenterAxisLabels(false)



            axisRightY.setDrawAxisLine(false)
            axisRightY.setDrawLabels(false)
            axisLeftY.setDrawAxisLine(false)
            axisLeftY.setDrawLabels(false)

            val barDataSet = BarDataSet(entries, "")
            barDataSet.color = Color.parseColor("#2C1C85")

            val data = BarData(barDataSet)
            data.setValueTextColor(Color.BLACK)
            data.setValueTextSize(10f)



            //binding.sumExpense.text = "Всего потрачено: \n${sum}₽"

            barChart.description.isEnabled = false
            barChart.setDrawBorders(false)
            barChart.data = data
            barChart.animateY(1300)

        }

    }

    private fun setLiterChart() {

        //https://russianblogs.com/article/2984929939/

        val barChart = binding.chartLiters
        val xAxis = barChart.xAxis
        val axisRightY: YAxis = barChart.axisRight
        val axisLeftY: YAxis = barChart.axisLeft
        val currentDate = SimpleDateFormat("M").format(Date())

        val entries = ArrayList<BarEntry>()


        fuelViewModel.getAllFuel().observe(this) {listExpense ->

            var sum = 0.0
            var listCostMonth = arrayListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)


            for (i in listExpense.indices){

                when(getMonth(listExpense[i].date)){

                    "01" -> listCostMonth[0] += listExpense[i].capacity
                    "02" -> listCostMonth[1] += listExpense[i].capacity
                    "03" -> listCostMonth[2] += listExpense[i].capacity
                    "04" -> listCostMonth[3] += listExpense[i].capacity
                    "05" -> listCostMonth[4] += listExpense[i].capacity
                    "06" -> listCostMonth[5] += listExpense[i].capacity
                    "07" -> listCostMonth[6] += listExpense[i].capacity
                    "08" -> listCostMonth[7] += listExpense[i].capacity
                    "09" -> listCostMonth[8] += listExpense[i].capacity
                    "10" -> listCostMonth[9] += listExpense[i].capacity
                    "11" -> listCostMonth[10] += listExpense[i].capacity
                    "12" -> listCostMonth[11] += listExpense[i].capacity
                }

                //sum += listExpense[i].cost

                //binding.monthCost.text = "${listCostMonth[currentDate.toInt()-1]}₽"
            }

            for (i in 1..12){
                entries.add(BarEntry(i.toFloat(), listCostMonth[i-1].toFloat()))
            }

            barChart.setBackgroundColor(Color.parseColor("#E4D8FF"))
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(false)
            xAxis.setCenterAxisLabels(false)



            axisRightY.setDrawAxisLine(false)
            axisRightY.setDrawLabels(false)
            axisLeftY.setDrawAxisLine(false)
            axisLeftY.setDrawLabels(false)

            val barDataSet = BarDataSet(entries, "")
            barDataSet.color = Color.parseColor("#5D2DA5")

            val data = BarData(barDataSet)
            data.setValueTextColor(Color.BLACK)
            data.setValueTextSize(10f)



            //binding.sumExpense.text = "Всего потрачено: \n${sum}₽"

            barChart.description.isEnabled = false
            barChart.setDrawBorders(false)
            barChart.data = data
            barChart.animateY(1300)

        }

    }

    fun getMonth(str : String) : String{
        return str.split("/").toTypedArray()[0]
    }

    private fun getFuels(){
        //var selectedCar = Car(-1, "-", "-", "sedan",-1, -1, "-", "-", "-", false)
        fuelViewModel.getAllFuel().observe(viewLifecycleOwner) {listFuel ->
            binding.amountRefill.text = listFuel.size.toString()
        }
    }




    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FuelPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}