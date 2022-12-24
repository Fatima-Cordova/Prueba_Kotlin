package com.example.prueba_kotlin.interfaces

import com.example.prueba_kotlin.model.CallOrders

interface OrderSelectListener {

    fun onOrdenSelect(orden: CallOrders)

}