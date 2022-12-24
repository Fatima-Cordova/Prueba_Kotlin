package com.example.prueba_kotlin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prueba_kotlin.Constants
import com.example.prueba_kotlin.model.CallOrders
import com.example.prueba_kotlin.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class OrderViewModel@Inject constructor (private val apiServices: ApiService) : ViewModel() {


    private val callOrder = MutableLiveData<List<CallOrders>>()


    fun callOrder() : LiveData<List<CallOrders>> {
        return callOrder
    }

    fun getCallOrder(){

        apiServices.getOrders().enqueue(object : Callback<List<CallOrders>> {
            override fun onResponse(
                call: Call<List<CallOrders>>,
                response: Response<List<CallOrders>>
            ) {
                if (!response.isSuccessful){
                    Log.d(Constants.CALL_ORDERS_LOGS, "getCallOrders:onResponse -> Not Successfuls")
                    //callOrder.postValue(null)
                    callOrder.postValue(null!!)
                    return
                }

                val remoteCallOrders = response.body();
                Log.d(Constants.CALL_ORDERS_LOGS, "getCallOrders:on Response->  ${remoteCallOrders}")

                if (remoteCallOrders != null){
                    val orders = remoteCallOrders
                    callOrder.postValue(orders!!)
                }else{
                    callOrder.postValue(null!!)
                }
            }

            override fun onFailure(call: Call<List<CallOrders>>, t: Throwable) {
                Log.d(Constants.CALL_ORDERS_LOGS, "getCallOrders:onFailur")
                callOrder.postValue(null!!)
            }

        })


    }



}
