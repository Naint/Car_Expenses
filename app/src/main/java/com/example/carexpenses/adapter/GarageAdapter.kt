package com.example.carexpenses.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.carexpenses.R
import com.example.carexpenses.data.Car
import com.example.carexpenses.screens.main.CarViewModel

class GarageAdapter(carViewModel: CarViewModel, context: Context, owner: LifecycleOwner): RecyclerView.Adapter<GarageAdapter.GarageViewHolder>() {

    private var carList = emptyList<Car>()
    private var carViewModel = carViewModel
    private var context = context
    private var owner = owner

    class GarageViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GarageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_garage_layout, parent, false)
        return GarageViewHolder(view)
    }

    override fun onBindViewHolder(holder: GarageViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_brand).text = carList[position].brand
        holder.itemView.findViewById<TextView>(R.id.tv_model).text = carList[position].model
        holder.itemView.findViewById<Button>(R.id.btn_edit).setOnClickListener{
            showEditCarDialog(carViewModel, context, position)
            notifyDataSetChanged()
            Log.i("info", "openDialog")
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Car>){
        carList = list
        notifyDataSetChanged()
    }


    //Переработать через ViewBinding
    private fun showEditCarDialog(carViewModel: CarViewModel, context: Context, position: Int){
        val builder = AlertDialog.Builder(context)
        val customView = LayoutInflater.from(context).inflate(R.layout.add_car_menu, null)
        builder.setView(customView)

        val buttonAddCar = customView.findViewById<Button>(R.id.saveCar)
        val buttonExit = customView.findViewById<Button>(R.id.exitCarMenu)

        val items = listOf("Седан", "Хетчбек", "Кроссовер", "Внедорожник")
        val autoComplete : AutoCompleteTextView = customView.findViewById(R.id.autoCompleteBodyType)
        val adapter = ArrayAdapter(context, R.layout.list_body_type, items)
        autoComplete.setAdapter(adapter)

        val dialog = builder.create()
        dialog.show()


        val brandTextView = customView.findViewById<EditText>(R.id.brandEditText)
        val modelTextView = customView.findViewById<EditText>(R.id.modelEditText)
        val bodyTypeTextView = autoComplete
        val mileageTextView = customView.findViewById<EditText>(R.id.mileageEditText)
        val createYearTextView = customView.findViewById<EditText>(R.id.createYearEditText)
        val vinTextView = customView.findViewById<EditText>(R.id.vinEditText)
        val fuelTypeTextView = customView.findViewById<EditText>(R.id.fuelTypeEditText)



        try{
            carViewModel.getAllCars().observe(owner){
                brandTextView.setText(it[position].brand)
                modelTextView.setText(it[position].model)
                //bodyTypeTextView.setText(it[position].bodyType)
                createYearTextView.setText(it[position].createYear.toString())
                mileageTextView.setText(it[position].mileage.toString())
                vinTextView.setText(it[position].vin)
            }
        }
        catch (e: java.lang.Exception){

        }

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->

            val selectedBodyType = adapterView.getItemAtPosition(i)
        }




        buttonAddCar.setOnClickListener{

            val brand = brandTextView.text.toString()
            val model = modelTextView.text.toString()
            val bodyType = bodyTypeTextView.text.toString()
            val mileage = mileageTextView.text.toString()
            val createYear = createYearTextView.text.toString()
            val vin = vinTextView.text.toString()
            val fuelType = fuelTypeTextView.text.toString()

            val car  = Car(carList[position].id, brand, model, bodyType, createYear.toInt(), mileage.toInt(), vin, fuelType, "-", true)
            carViewModel.update(car){}
        }


        buttonExit?.setOnClickListener{
            dialog.cancel()
        }

    }

    private fun addCarInDb(customView: View){
        val brandTextView = customView.findViewById<EditText>(R.id.brandEditText)
        val modelTextView = customView.findViewById<EditText>(R.id.modelEditText)
        val mileageTextView = customView.findViewById<EditText>(R.id.mileageEditText)
        val createYearTextView = customView.findViewById<EditText>(R.id.createYearEditText)
        val vinTextView = customView.findViewById<EditText>(R.id.vinEditText)
        val fuelTypeTextView = customView.findViewById<EditText>(R.id.fuelTypeEditText)
    }


}