package com.example.carexpenses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.carexpenses.databinding.ActivityMainBinding
import com.example.carexpenses.screens.ListExpenseFragment
import com.example.carexpenses.screens.history.HistoryFragment
import com.example.carexpenses.screens.main.MainFragment
import com.example.carexpenses.screens.OtherFragment
import com.example.carexpenses.screens.TimeSheetFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(MainFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.main -> replaceFragment(MainFragment())
                R.id.history -> replaceFragment(HistoryFragment())
                R.id.map -> replaceFragment(TimeSheetFragment())
                R.id.other -> replaceFragment(OtherFragment())
                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}