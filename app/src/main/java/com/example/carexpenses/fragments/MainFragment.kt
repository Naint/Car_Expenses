package com.example.carexpenses.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carexpenses.R
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.Expense
import com.example.carexpenses.databinding.FragmentMainBinding
import com.example.carexpenses.screens.main.CarViewModel
import com.example.carexpenses.screens.main.ExpenseViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var carViewModel: CarViewModel
    lateinit var expenseViewModel: ExpenseViewModel

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var selectedCarId = 8

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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel.initDataBase()
        //carViewModel.insert(Car(null, "Honda", "Civic", 2020, 2000, "FK100", "100", "-", true)){}
        getCars()
        onClickListeners()
        expenseViewModel.initTable()



    }

    //Переписать по возможности
    private fun getCars(){
        var selectedCar = Car(-1, "-", "-", -1, -1, "-", "-", "-", false)
        carViewModel.getAllCars().observe(this) {listCar ->
            for (i in 0..listCar.size-1){
                if(listCar[i].selectedCar == true){
                    selectedCar = listCar[i]
                }
            }
            setInfoUpperPanel(selectedCar)
        }
    }





    private fun setInfoUpperPanel(selectedCar : Car){
        binding.carModelTextView.text = "${selectedCar.brand} ${selectedCar.model}"
        binding.mileageTextView.text = "Пробег: ${selectedCar.mileage} км"
        binding.typeFuelTextView.text = "Тип топлива: ${selectedCar.fuelType}"
        binding.bankCapacityTextView.text = "Год выпуска: ${selectedCar.createYear}"

    }



    private fun onClickListeners(){
        binding.washCardView.setOnClickListener{
            showWashesDialog()
        }

        binding.refillCardView.setOnClickListener{
            showRefillDialog()
        }

        binding.parkingCardView.setOnClickListener{
            showAddParkingDialog()
        }

        binding.ticketCardView.setOnClickListener{
            showAddTicketDialog()
        }

        binding.addCarButton.setOnClickListener{
            showAddCarDialog()
        }

    }






    private fun showRefillDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_refill_gas_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonExit = customView.findViewById<Button>(R.id.exitRefillDialog)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
    }

    private fun showWashesDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_wash_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonExit = customView.findViewById<Button>(R.id.exitWashDialog)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }

        val buttonSave = customView.findViewById<Button>(R.id.saveWashInfo)
        buttonSave?.setOnClickListener{

            val typeExpense = "Мойка"
            val mileage = customView.findViewById<EditText>(R.id.mileageInfoWash).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.dateInfoWash).text.toString()
            val cost = customView.findViewById<EditText>(R.id.costInfoWash).text.toString().toInt()
            val comment = customView.findViewById<EditText>(R.id.commentInfoWash).text.toString()
            expenseViewModel.insert(Expense(null, typeExpense, mileage, date, cost, comment, selectedCarId)){}
        }

    }



    private fun showAddParkingDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_parking_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonExit = customView.findViewById<Button>(R.id.exitParkingDialog)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
        val buttonSave = customView.findViewById<Button>(R.id.saveParkingInfo)
        buttonSave?.setOnClickListener{
            val typeExpense = "Парковка"
            val mileage = customView.findViewById<EditText>(R.id.mileageInfoParking).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.dateInfoParking).text.toString()
            val cost = customView.findViewById<EditText>(R.id.costInfoParking).text.toString().toInt()
            val comment = customView.findViewById<EditText>(R.id.commentInfoParking).text.toString()
            expenseViewModel.insert(Expense(null, typeExpense, mileage, date, cost, comment, selectedCarId)){}
        }


    }

    private fun showAddTicketDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_ticket_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonExit = customView.findViewById<Button>(R.id.exitTicketDialog)
        val buttonSave = customView.findViewById<Button>(R.id.saveTicketInfo)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
        buttonSave?.setOnClickListener{

            val typeExpense = "Штраф"
            val mileage = customView.findViewById<EditText>(R.id.mileageInfoTicket).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.dateInfoTicket).text.toString()
            val cost = customView.findViewById<EditText>(R.id.costInfoTicket).text.toString().toInt()
            val comment = customView.findViewById<EditText>(R.id.commentInfoTicket).text.toString()
            expenseViewModel.insert(Expense(null, typeExpense, mileage, date, cost, comment, selectedCarId)){}
        }

    }

    private fun showAddCarDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_car_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonAddCar = customView.findViewById<Button>(R.id.saveCar)

        buttonAddCar.setOnClickListener{

            val brand = customView.findViewById<EditText>(R.id.brandEditText).text.toString()
            val model = customView.findViewById<EditText>(R.id.modelEditText).text.toString()
            val mileage = customView.findViewById<EditText>(R.id.mileageEditText).text.toString()
            val createYear = customView.findViewById<EditText>(R.id.createYearEditText).text.toString()
            val vin = customView.findViewById<EditText>(R.id.vinEditText).text.toString()
            val fuelType = customView.findViewById<EditText>(R.id.fuelTypeEditText).text.toString()

            carViewModel.insert(Car(null, brand, model, 2020, 20, vin, fuelType, "-", false)){}
        }

        val buttonExit = customView.findViewById<Button>(R.id.exitCarMenu)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}