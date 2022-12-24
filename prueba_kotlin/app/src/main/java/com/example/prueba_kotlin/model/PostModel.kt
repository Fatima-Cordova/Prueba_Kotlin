package com.example.prueba_kotlin.model

import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("orderId"       ) var orderId       : Int    = 0,
    @SerializedName("username"      ) var username      : String = "",
    @SerializedName("subTotal"      ) var subTotal      : Double = 0.0,
    @SerializedName("ticketNumber"  ) var ticketNumber  : Int    = 0,
    @SerializedName("orderDateTime" ) var orderDateTime : String = "",
    @SerializedName("orderType"     ) var orderType     : Int    = 0
)