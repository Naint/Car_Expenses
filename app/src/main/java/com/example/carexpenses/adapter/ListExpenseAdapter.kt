package com.example.carexpenses.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carexpenses.R
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.Expense
import org.w3c.dom.Text

class ListExpenseAdapter: RecyclerView.Adapter<ListExpenseAdapter.ListExpenseViewHolder>() {

    private var expenseList = emptyList<Expense>()

    class ListExpenseViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense_list, parent, false)
        return ListExpenseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: ListExpenseViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvExpenseName).text = expenseList[position].typeExpense
        holder.itemView.findViewById<TextView>(R.id.tvData).text = expenseList[position].date
        holder.itemView.findViewById<TextView>(R.id.tvPrice).text = expenseList[position].cost.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Expense>, typeExpense: String){
        var buffList: ArrayList<Expense> = ArrayList()

        for(i in list){
            if(i.typeExpense == typeExpense)
                buffList.add(i)
        }
        expenseList = buffList
        notifyDataSetChanged()
    }
}