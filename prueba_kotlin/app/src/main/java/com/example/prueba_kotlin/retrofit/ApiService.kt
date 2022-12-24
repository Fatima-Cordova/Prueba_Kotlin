package com.example.prueba_kotlin.retrofit

import com.example.prueba_kotlin.model.CallOrders
import com.example.prueba_kotlin.model.PostModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ApiService {

    @GET("api/test/getOrders")
    fun getOrders() : Call<List<CallOrders>>

    @PUT("api/test/updateOrder/")
    fun updateOrder(@Body request : CallOrders) : Call<PostModel>
}