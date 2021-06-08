package com.protoEnergy.protoenergy.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.protoEnergy.protoenergy.data.OrderList
import com.protoEnergy.protoenergy.databinding.FragmentOrderBinding


class OrdersRecyclerViewAdapter() : RecyclerView.Adapter<OrdersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    var data = listOf<OrderList>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = data[position]
        holder.bind(order)

    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: FragmentOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OrderList) {
            binding.theOrder = item
            binding.executePendingBindings()

        }

    }

}