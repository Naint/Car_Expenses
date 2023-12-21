package com.example.carexpenses.screens.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carexpenses.adapter.ViewPagerAdapter
import com.example.carexpenses.databinding.FragmentHistoryBinding
import com.example.carexpenses.databinding.FragmentMainBinding
import com.example.carexpenses.screens.history.FuelPageFragment
import com.example.carexpenses.screens.history.ReviewPageFragment
import com.example.carexpenses.screens.ServicesPageFragment
import com.example.carexpenses.screens.TripsPageFragment
import com.google.android.material.tabs.TabLayoutMediator


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentHistoryBinding? = null
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
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList  = arrayListOf(ReviewPageFragment(), FuelPageFragment())

        binding.apply {
            viewPager.adapter = ViewPagerAdapter(fragmentList, this@HistoryFragment.parentFragmentManager, lifecycle)

            TabLayoutMediator(tabView, viewPager){ tab, position ->
                when(position){

                    0 -> { tab.text = "Расходы"
                    }
                    1 -> { tab.text = "Топливо"
                    }
                }
            }.attach()

        }

    }




    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}