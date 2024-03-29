package com.example.carexpenses.screens.main

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.carexpenses.R
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.Expense
import com.example.carexpenses.data.Refill
import com.example.carexpenses.databinding.FragmentMainBinding
import com.example.carexpenses.screens.history.FuelViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var carViewModel: CarViewModel
    lateinit var expenseViewModel: ExpenseViewModel
    lateinit var fuelViewModel: FuelViewModel

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var selectedCarId = 1

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
        carViewModel = ViewModelProvider(this)[CarViewModel::class.java]
        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        fuelViewModel = ViewModelProvider(this)[FuelViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        carViewModel.initDataBase()
        expenseViewModel.initTable()
        fuelViewModel.initTable()
        getCars()
        //setCarBodyIcon(Car(-1, "-", "-", "Кроссовер",-1, -1, "-", "-", "-", false))
        onClickListeners()
    }

    private fun getCars(){
        var selectedCar = Car(-1, "-", "-", "sedan",-1, -1, "-", "-", "-", false)
        carViewModel.getAllCars().observe(viewLifecycleOwner) {listCar ->

            if (listCar.isEmpty()){
                showAddCarDialog()
            }

            for (i in listCar.indices){
                if(listCar[i].selectedCar){
                    selectedCar = listCar[i]
                }
            }
            setInfoUpperPanel(selectedCar)
            setCarBodyIcon(selectedCar)
        }
    }

    private fun setInfoUpperPanel(selectedCar : Car){
        binding.carModelTextView.text = "${selectedCar.brand} ${selectedCar.model}"
        binding.mileageTextView.text = "Пробег: ${selectedCar.mileage} км"
    }

    private fun setCarBodyIcon(car: Car){

        val carImagesMap = mutableMapOf(
            "Седан" to R.drawable.sedan,
            "Кроссовер" to R.drawable.crossover,
            "Хетчбек" to R.drawable.hatchback,
            "Внедорожник" to R.drawable.jeep)

        carImagesMap[car.bodyType]?.let { binding.car.setImageResource(it) }

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

        binding.otherCardView.setOnClickListener{
            showAddOtherDialog()
        }

        binding.tuningCardView.setOnClickListener{
            showAddTuningDialog()
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

        var ed = customView.findViewById<EditText>(R.id.dateRefill)
        ed.transformIntoDatePicker(requireContext(),"MM/dd/yyyy")

        val buttonExit = customView.findViewById<Button>(R.id.exitRefillDialog)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }

        val buttonSave = customView.findViewById<Button>(R.id.saveRefillInfo)
        buttonSave?.setOnClickListener{

            val mileage = customView.findViewById<EditText>(R.id.mileageRefill).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.dateRefill).text.toString()
            val cost = customView.findViewById<EditText>(R.id.costRefill).text.toString().toDouble()
            val fuelType = customView.findViewById<EditText>(R.id.typeFuelRefill).text.toString()
            val capacity = customView.findViewById<EditText>(R.id.capacityRefill).text.toString().toInt()
            val costPerLiter = customView.findViewById<EditText>(R.id.costPerLiterRefill).text.toString().toDouble()
            val comment = customView.findViewById<EditText>(R.id.commentRefill).text.toString()

            fuelViewModel.insert(Refill(null, date, mileage, cost, fuelType, capacity, costPerLiter, comment, selectedCarId)){}

        }

    }

    private fun showWashesDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_wash_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        var date = customView.findViewById<EditText>(R.id.dateInfoWash)
        date.transformIntoDatePicker(requireContext(),"MM/dd/yyyy")

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

        val ed = customView.findViewById<EditText>(R.id.dateInfoParking)
        ed.transformIntoDatePicker(requireContext(),"MM/dd/yyyy")

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

        val ed = customView.findViewById<EditText>(R.id.dateInfoTicket)
        ed.transformIntoDatePicker(requireContext(),"MM/dd/yyyy")

        val buttonExit = customView.findViewById<Button>(R.id.exitTicketDialog)
        val buttonSave = customView.findViewById<Button>(R.id.saveTicketInfo)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
        buttonSave?.setOnClickListener{

            val typeExpense = "Штрафы"
            val mileage = customView.findViewById<EditText>(R.id.mileageInfoTicket).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.dateInfoTicket).text.toString()
            val cost = customView.findViewById<EditText>(R.id.costInfoTicket).text.toString().toInt()
            val comment = customView.findViewById<EditText>(R.id.commentInfoTicket).text.toString()
            expenseViewModel.insert(Expense(null, typeExpense, mileage, date, cost, comment, selectedCarId)){}
        }

    }

    private fun showAddOtherDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_other_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val ed = customView.findViewById<EditText>(R.id.etDateOther)
        ed.transformIntoDatePicker(requireContext(),"MM/dd/yyyy")

        val buttonExit = customView.findViewById<Button>(R.id.btnExitOther)
        val buttonSave = customView.findViewById<Button>(R.id.btnSaveOther)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
        buttonSave?.setOnClickListener{

            val typeExpense = "Прочее"
            val mileage = customView.findViewById<EditText>(R.id.etMileageOther).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.etDateOther).text.toString()
            val cost = customView.findViewById<EditText>(R.id.etPriceOther).text.toString().toInt()
            val comment = customView.findViewById<EditText>(R.id.etCommentOther).text.toString()
            expenseViewModel.insert(Expense(null, typeExpense, mileage, date, cost, comment, selectedCarId)){}
        }

    }

    private fun showAddTuningDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_tunning_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val ed = customView.findViewById<EditText>(R.id.etDateTuning)
        ed.transformIntoDatePicker(requireContext(),"MM/dd/yyyy")

        val buttonExit = customView.findViewById<Button>(R.id.btnExitTuning)
        val buttonSave = customView.findViewById<Button>(R.id.btnSaveTuning)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
        buttonSave?.setOnClickListener{

            val typeExpense = "Тюнинг"
            val mileage = customView.findViewById<EditText>(R.id.etMileageTuning).text.toString().toInt()
            val date = customView.findViewById<EditText>(R.id.etDateTuning).text.toString()
            val cost = customView.findViewById<EditText>(R.id.etPriceTuning).text.toString().toInt()
            val comment = customView.findViewById<EditText>(R.id.etCommentTuning).text.toString()
            expenseViewModel.insert(Expense(null, typeExpense, mileage, date, cost, comment, selectedCarId)){}
        }

    }

    private fun showAddCarDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_car_menu, null)
        builder.setView(customView)

        val buttonAddCar = customView.findViewById<Button>(R.id.saveCar)
        val buttonExit = customView.findViewById<Button>(R.id.exitCarMenu)

        val items = listOf<String>("Седан", "Хетчбек", "Кроссовер", "Внедорожник")
        val autoComplete : AutoCompleteTextView = customView.findViewById(R.id.autoCompleteBodyType)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_body_type, items)
        autoComplete.setAdapter(adapter)
        var bodyType = ""

        val dialog = builder.create()
        dialog.show()


        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->

            val selectedBodyType = adapterView.getItemAtPosition(i)
        }


        buttonAddCar.setOnClickListener{

            val brand = customView.findViewById<EditText>(R.id.brandEditText).text.toString()
            val model = customView.findViewById<EditText>(R.id.modelEditText).text.toString()
            val bodyType = autoComplete.text.toString()
            val mileage = customView.findViewById<EditText>(R.id.mileageEditText).text.toString()
            val createYear = customView.findViewById<EditText>(R.id.createYearEditText).text.toString()
            val vin = customView.findViewById<EditText>(R.id.vinEditText).text.toString()
            val fuelType = customView.findViewById<EditText>(R.id.fuelTypeEditText).text.toString()

            val car  = Car(null, brand, model, bodyType, createYear.toInt(), mileage.toInt(), vin, fuelType, "-", true)
            Log.i("info", car.id.toString())
            carViewModel.insert(car){}
            //unSetSelectedCars()
        }


        buttonExit?.setOnClickListener{
            dialog.cancel()
        }

    }


    fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
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