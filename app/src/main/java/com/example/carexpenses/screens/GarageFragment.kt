package com.example.carexpenses.screens

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.carexpenses.R
import com.example.carexpenses.adapter.GarageAdapter
import com.example.carexpenses.data.Car
import com.example.carexpenses.databinding.FragmentGarageBinding
import com.example.carexpenses.databinding.FragmentMainBinding
import com.example.carexpenses.screens.main.CarViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




class GarageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentGarageBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: GarageAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var carViewModel: CarViewModel

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
        carViewModel = ViewModelProvider(this)[CarViewModel::class.java]
        _binding = FragmentGarageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        recyclerView = binding.rvGarage
        adapter = GarageAdapter(carViewModel)
        recyclerView.adapter = adapter
        carViewModel.getAllCars().observe(viewLifecycleOwner){
            adapter.setList(it)
        }

        binding.btnAddCar.setOnClickListener{
            showAddCarDialog()
            carViewModel.getAllCars().observe(viewLifecycleOwner){
                adapter.setList(it)
            }
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

            val car  = Car(null, brand, model, bodyType, createYear.toInt(), mileage.toInt(), vin, fuelType, "-", false)

            carViewModel.insert(car){}
        }


        buttonExit?.setOnClickListener{
            dialog.cancel()
        }

    }

    private fun addCarInAdapter(){

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GarageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}