package com.example.prueba_kotlin.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_order")
data class AllOrders (
    @PrimaryKey(autoGenerate = false)
    var OrderId : Int?,
    var Username : String?,
    var SubTotal : Double?,
    var TicketNumber : Int?,
    var OrderDateTime : String?,
    var  OrderType : Int?,
)