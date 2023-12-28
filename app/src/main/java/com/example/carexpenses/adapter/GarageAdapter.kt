package com.example.carexpenses.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.carexpenses.R
import com.example.carexpenses.data.Car
import com.example.carexpenses.screens.main.CarViewModel
import com.example.carexpenses.screens.main.ExpenseViewModel

class GarageAdapter(carViewModel: CarViewModel): RecyclerView.Adapter<GarageAdapter.GarageViewHolder>() {

    private var carList = emptyList<Car>()
    private var carViewModel = carViewModel

    class GarageViewHolder(view: View): RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GarageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_garage_layout, parent, false)
        return GarageViewHolder(view)
    }

    override fun onBindViewHolder(holder: GarageViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_brand).text = carList[position].brand
        holder.itemView.findViewById<TextView>(R.id.tv_model).text = carList[position].model
        holder.itemView.findViewById<Button>(R.id.btn_edit).setOnClickListener{
            carViewModel.insert(Car(null, "test", "test", "test", 2020, 2020, "fk7", "s", "s", false)){}
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    private fun setChangedInDb(carViewModel: CarViewModel){


    }



    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Car>){
        carList = list
        notifyDataSetChanged()
    }

    private fun showAddCarDialog(carViewModel: CarViewModel, context: Context){
        val builder = AlertDialog.Builder(context)
        val customView = LayoutInflater.from(context).inflate(R.layout.add_car_menu, null)
        builder.setView(customView)

        val buttonAddCar = customView.findViewById<Button>(R.id.saveCar)
        val buttonExit = customView.findViewById<Button>(R.id.exitCarMenu)

        val items = listOf<String>("Седан", "Хетчбек", "Кроссовер", "Внедорожник")
        val autoComplete : AutoCompleteTextView = customView.findViewById(R.id.autoCompleteBodyType)
        val adapter = ArrayAdapter(context, R.layout.list_body_type, items)
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


}