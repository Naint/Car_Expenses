package com.example.carexpenses.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carexpenses.R
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.MainDB
import com.example.carexpenses.databinding.FragmentMainBinding
import com.example.carexpenses.viewmodels.CarViewModel
import java.nio.file.attribute.AclEntry.Builder
import java.util.*
import kotlin.concurrent.schedule

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

     lateinit var carViewModel: CarViewModel

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        carViewModel = ViewModelProvider(this)[CarViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



 /*       carViewModel.getAllCars().observe(this, {

        })*/

        /*val car = Car(null, "Honda", "Civic", 2019, 20, "FK7-1001764", "Дизель", "Info")
        carViewModel.insertCar(car)*/


        /*val db = MainDB.getDatabase(requireContext())*/
        carViewModel.initDataBase()
        binding.washCardView.setOnClickListener{
            val buff = showWashesDialog()
        }

        binding.refillCardView.setOnClickListener{
            showRefillDialog()
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


    private fun showWashesDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_wash_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonExit = customView.findViewById<Button>(R.id.buttonExit)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
    }

    private fun showRefillDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_refill_gas_menu, null)
        builder.setView(customView)

        val dialog = builder.create()
        dialog.show()

        val buttonExit = customView.findViewById<Button>(R.id.buttonExit)
        buttonExit?.setOnClickListener{
            dialog.cancel()
        }
    }




}