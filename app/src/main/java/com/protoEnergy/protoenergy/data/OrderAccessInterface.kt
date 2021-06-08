package com.protoEnergy.protoenergy.data

import retrofit2.Call
import retrofit2.http.GET


interface endPoint {
    @GET("orders.json")
    fun gettingOrders(): Call<List<OrderList>>
}