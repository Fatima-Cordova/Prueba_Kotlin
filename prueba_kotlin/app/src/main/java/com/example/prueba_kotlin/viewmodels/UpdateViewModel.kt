package com.example.prueba_kotlin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prueba_kotlin.Constants
import com.example.prueba_kotlin.model.CallOrders
import com.example.prueba_kotlin.model.PostModel
import com.example.prueba_kotlin.retrofit.ApiService
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor (private val apiService: ApiService) : ViewModel() {

    var intResult : Boolean = false
    private val callOrder = MutableLiveData<List<CallOrders>>()

    fun callOrder() : LiveData<List<CallOrders>> {
        return callOrder
    }

    fun getStatusUpdate() : Boolean{
        return intResult
    }

    fun updateOrder(orden: CallOrders) {

        val json = Gson().toJson(orden)
        Log.d("Register Users", json)

        apiService.updateOrder(orden).enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                intResult = response.isSuccessful
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                Log.d(Constants.CALL_ORDERS_LOGS, "getCallOrders:onFailure")
                callOrder.postValue(null!!)
                intResult = false
            }

        })

    }

}

