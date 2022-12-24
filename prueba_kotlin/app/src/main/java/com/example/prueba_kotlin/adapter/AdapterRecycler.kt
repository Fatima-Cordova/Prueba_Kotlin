package com.example.prueba_kotlin.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_kotlin.R
import com.example.prueba_kotlin.interfaces.OrderSelectListener
import com.example.prueba_kotlin.model.CallOrders

class AdapterRecycler (val constext : Context,
                       var callOrder : ArrayList<CallOrders>,
                       val listener : OrderSelectListener): RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {
    private val callResultOrders = ArrayList<CallOrders>()

    init {
        callResultOrders.addAll(callOrder)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecycler.ViewHolder {
        val view = LayoutInflater.from(constext).inflate(R.layout.card_post, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tv_order : TextView = view.findViewById(R.id.tv_order)
        val tv_order_time : TextView = view.findViewById(R.id.tv_order_time)
        val tv_order_dlivery : TextView = view.findViewById(R.id.tv_order_dlivery)
        val viewsConstrains : ConstraintLayout = view.findViewById(R.id.constraint_view)
        init {
            view.setOnClickListener{
                listener.onOrdenSelect(callOrder[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: AdapterRecycler.ViewHolder, position: Int) {

        val order = callOrder
        holder.apply {

            tv_order.text = "Order # ${order[position].orderId} User: ${order[position].username} Total: $ ${order[position].subTotal} Ticket: ${order[position].ticketNumber} "
            tv_order_time.text = "Order Time: ${order[position].orderDateTime}"

            when (order[position].orderType){
                1 -> tv_order_dlivery.text = "ToGo"
                2 -> tv_order_dlivery.text = "Dine"
                3 -> tv_order_dlivery.text = "Pickup"
                4 -> tv_order_dlivery.text = "Delivery"
            }

            when (order[position].orderType){
                1 -> viewsConstrains.setBackgroundColor(Color.parseColor("#66bb6a"))
                2 -> viewsConstrains.setBackgroundColor(Color.parseColor("#78909C"))
                3 -> viewsConstrains.setBackgroundColor(Color.parseColor("#FFA726"))
                4 -> viewsConstrains.setBackgroundColor(Color.parseColor("#42A5F5"))
            }


        }
    }

    override fun getItemCount(): Int = callOrder.size

    fun setData(list : ArrayList<CallOrders>){
        callOrder = list
        notifyDataSetChanged()
    }

}