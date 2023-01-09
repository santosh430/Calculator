package com.example.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemCalcHistoryBinding


class AdapterCalcHistory(private var arrayListCalcHistory: ArrayList<String>)
    : RecyclerView.Adapter<AdapterCalcHistory.ViewHolder>() {

    inner class ViewHolder(binding: ItemCalcHistoryBinding):RecyclerView.ViewHolder(binding.root){
        val tvCalcHistory = binding.tvHistory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCalcHistory.ViewHolder {
        val view = ItemCalcHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCalcHistory.text = arrayListCalcHistory[position]
    }

    override fun getItemCount(): Int {
        return arrayListCalcHistory.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateCalcHistory(list :ArrayList<String>){
        arrayListCalcHistory = list
        notifyDataSetChanged()
    }
}
