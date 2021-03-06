//package com.protoEnergy.protoenergy.data
//
//import android.content.Context
//import android.net.ConnectivityManager
//import okhttp3.Interceptor
//import okhttp3.Response
//
//class NetworkConnectionInterceptor() : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        if (isConnected()) {
//            throw NoConnectivityException()
//        }
//        val builder = chain.request().newBuilder()
//        return chain.proceed(builder.build())
//    }
//
//    fun isConnected(): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val netInfo = connectivityManager.activeNetwork
//        return (netInfo != null)
//    }
//
//}
