package com.protoEnergy.protoenergy.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject


class ServiceBuilder @Inject constructor() {


    suspend fun connectingToEndpoint(): Call<List<OrderList>> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://proto-energy.github.io/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(endPoint::class.java)
        return withContext(Dispatchers.IO) { service.gettingOrders() }
    }

}