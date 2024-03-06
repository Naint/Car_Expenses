package com.example.carexpenses.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.carexpenses.R
import com.example.carexpenses.adapter.GarageAdapter
import com.example.carexpenses.adapter.ListExpenseAdapter
import com.example.carexpenses.data.Expense
import com.example.carexpenses.databinding.FragmentGarageBinding
import com.example.carexpenses.databinding.FragmentListExpenseBinding
import com.example.carexpenses.screens.main.CarViewModel
import com.example.carexpenses.screens.main.ExpenseViewModel
import kotlin.collections.ArrayList
import kotlin.math.exp

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListExpenseFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var adapter: ListExpenseAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var expenseViewModel: ExpenseViewModel

    private var _binding : FragmentListExpenseBinding? = null
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
        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        _binding = FragmentListExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners(){
        binding.apply {
            cvWash.setOnClickListener{
                initList("Мойка")
            }
            cvTunning.setOnClickListener{
                initList("Тюнинг")
            }
            cvTicket.setOnClickListener{
                initList("Штрафы")
            }
            cvParking.setOnClickListener{
                initList("Парковка")
            }
            cvRefill.setOnClickListener{
                initList("Заправка")
            }
            cvOther.setOnClickListener{
                initList("Прочее")
            }
        }
    }

    private fun initList(type: String){
        recyclerView = binding.rvListExpense
        adapter = ListExpenseAdapter()
        recyclerView.adapter = adapter
        expenseViewModel.getAllExpense().observe(viewLifecycleOwner){
            adapter.setList(it, type)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListExpenseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}