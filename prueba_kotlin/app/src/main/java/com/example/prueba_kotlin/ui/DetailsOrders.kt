package com.example.prueba_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.prueba_kotlin.Constants
import com.example.prueba_kotlin.R
import com.example.prueba_kotlin.adapter.AdapterRecycler
import com.example.prueba_kotlin.adapter.AdapterSpinner
import com.example.prueba_kotlin.dao.AllOrders
import com.example.prueba_kotlin.dao.OrderDao
import com.example.prueba_kotlin.dao.database.OrdersDB
import com.example.prueba_kotlin.databinding.ActivityDetailsOrdersBinding
import com.example.prueba_kotlin.interfaces.OrderSelectListener
import com.example.prueba_kotlin.model.CallOrders
import com.example.prueba_kotlin.utils.UtilPopError
import com.example.prueba_kotlin.viewmodels.UpdateViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class DetailsOrders : AppCompatActivity() {

    private lateinit var orders: CallOrders
    private lateinit var  binding : ActivityDetailsOrdersBinding
    internal var type = arrayOf("ToGo", "Dine", "Pickup", "Delivery")

    private var type_seleccionado  = ""
    private var typeDelivery : Int = 0
    private val updateViewModel : UpdateViewModel by viewModels()
    private var orderID : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orders = Gson().fromJson(intent.getStringExtra(Constants.ORDER), CallOrders::class.java)
        orderID = Integer.parseInt(orders.orderId.toString())

        binding.etUserNameUpdate.setText(orders.username.toString())
        binding.etSubTotalUpdate.setText(orders.subTotal.toString())
        binding.etTicketNumberUpdate.setText(orders.ticketNumber.toString())
        binding.etOrderTimeUpdate.setText(orders.orderDateTime.toString())

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                type_seleccionado = type[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val customAdapter = AdapterSpinner(applicationContext, type)
        binding.spinner.adapter = customAdapter


        binding.btnUpdateOrder.setOnClickListener{

            val name = binding.etUserNameUpdate.text
            val subtotal = binding.etSubTotalUpdate.text
            val ticket = binding.etTicketNumberUpdate.text
            val timedate = binding.etOrderTimeUpdate.text

            if (name.isNullOrEmpty()){
                Toast.makeText(applicationContext, getString(R.string.msg_username), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (subtotal.isNullOrEmpty()){
                Toast.makeText(applicationContext, getString(R.string.msg_subtotal), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            when(type_seleccionado){
                "ToGo"-> typeDelivery = 1
                "Dine"-> typeDelivery = 2
                "Pickup"-> typeDelivery = 3
                "Delivery"-> typeDelivery = 4
            }

            val order =CallOrders()
            order.orderId = orderID
            order.username = name.toString()
            order.subTotal = subtotal.toString().toDouble()
            order.ticketNumber = Integer.parseInt(ticket.toString())
            order.orderDateTime = formatDate(10)
            order.orderType = typeDelivery
            updateViewModel.updateOrder(order)
            Toast.makeText(applicationContext,  getString(R.string.msg_updateOrder), Toast.LENGTH_LONG).show()

            val intent = Intent(applicationContext, ActivityRecall::class.java)
            intent.putExtra(Constants.ORDER, Gson().toJson(orders))
            startActivity(intent)
        }
    }


    private fun formatDate(milliseconds: Long): String? {
        val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss")
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliseconds)
        val timeZone: TimeZone = TimeZone.getDefault()
        dateFormat.setTimeZone(timeZone)
        return dateFormat.format(calendar.time)
    }
}