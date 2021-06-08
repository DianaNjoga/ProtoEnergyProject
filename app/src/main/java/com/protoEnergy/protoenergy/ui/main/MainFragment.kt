package com.protoEnergy.protoenergy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.protoEnergy.protoenergy.R
import com.protoEnergy.protoenergy.data.OrderList
import com.protoEnergy.protoenergy.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: OrdersRecyclerViewAdapter
    var status = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.filterButton.setOnClickListener {
            showPopup(binding.filterButton)
        }
        viewModel.listOfOrders.observe(viewLifecycleOwner, { orders ->
            getList(orders)
        })
        viewModel.visible.observe(viewLifecycleOwner, { visibility ->
            binding.loading.visibility = if (visibility == true) View.VISIBLE
            else
                View.GONE
        })
        binding.list.layoutManager = LinearLayoutManager(this.context)
        adapter = OrdersRecyclerViewAdapter()
        binding.list.adapter = adapter
        return binding.root
    }

    private fun getList(orders: List<OrderList>) {
        adapter.data = orders
        adapter.notifyDataSetChanged()
    }

    fun showPopup(v: View) {
        this.context?.let {
            PopupMenu(it, v).apply {
                setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        when (item?.itemId) {
                            R.id.pending -> {
                                status = "Pending"
                                viewModel.filter(status)
                            }
                            R.id.cancelled -> {
                                status = "Canceled"
                                viewModel.filter(status)
                            }
                            R.id.all -> {
                                status = ""
                                viewModel.filter(status)
                            }
                        }
                        return true
                    }
                })
                inflate(R.menu.filter_overflow)
                show()
            }
        }
    }


}