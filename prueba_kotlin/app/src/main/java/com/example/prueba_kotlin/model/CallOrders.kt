package com.example.prueba_kotlin.model

import com.google.gson.annotations.SerializedName

class CallOrders {
    @SerializedName("orderId"       ) var orderId       : Int?    = null
    @SerializedName("username"      ) var username      : String? = null
    @SerializedName("subTotal"      ) var subTotal      : Double? = null
    @SerializedName("ticketNumber"  ) var ticketNumber  : Int?    = null
    @SerializedName("orderDateTime" ) var orderDateTime : String? = null
    @SerializedName("orderType"     ) var orderType     : Int?    = null
}

//    @SerializedName("orderId"       ) var orderId       : Int    = 0,
//    @SerializedName("username"      ) var username      : String = "",
//    @SerializedName("subTotal"      ) var subTotal      : Double = 0.0,
//    @SerializedName("ticketNumber"  ) var ticketNumber  : Int    = 0,
//    @SerializedName("orderDateTime" ) var orderDateTime : String = "",
//    @SerializedName("orderType"     ) var orderType     : Int    = 0
