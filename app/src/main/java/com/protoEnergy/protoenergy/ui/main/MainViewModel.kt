package com.protoEnergy.protoenergy.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.protoEnergy.protoenergy.Network.NoConnectivityException
import com.protoEnergy.protoenergy.data.OrderList
import com.protoEnergy.protoenergy.data.ServiceBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var caller: ServiceBuilder

    val listOfOrders: LiveData<List<OrderList>>
        get() = _listOfOrders
    private val _listOfOrders = MutableLiveData<List<OrderList>>()
    private val _filterList = MutableLiveData<List<OrderList>>()
    private var _visible: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val visible: LiveData<Boolean>
        get() = _visible


    init {
        _visible.value = true
        gettingLists()
    }



    fun gettingLists() {
        viewModelScope.launch{
            caller= ServiceBuilder()
            caller.connectingToEndpoint().enqueue(object : Callback<List<OrderList>> {
                override fun onResponse(
                    call: Call<List<OrderList>>,
                    response: Response<List<OrderList>>
                ) {
                    _listOfOrders.value = response.body()
                    _filterList.value = response.body()
                    _visible.value = false
                    Log.i("Proto-energy Json list", "$_listOfOrders")
                }

                override fun onFailure(call: Call<List<OrderList>>, t: Throwable) {
                    if(  t is NoConnectivityException) {
                        Log.i("netMessage",t.message)
                    }
                }

            })
        }

    }

    fun filter(status: String) {
        _visible.value = true
        if (status != "") {
            val eList = ArrayList<OrderList>()
            (_filterList.value as MutableList<OrderList>).forEach { x ->
                if (x.status == status) {
                    eList.add(x)
                }
            }
            Log.i("Proto-energy Json list", "$eList")
            _listOfOrders.value = eList
        } else
            _listOfOrders.value = _filterList.value
        _visible.value = false
    }

}