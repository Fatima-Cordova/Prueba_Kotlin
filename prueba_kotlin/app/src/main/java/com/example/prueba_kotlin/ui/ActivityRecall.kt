package com.example.prueba_kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.prueba_kotlin.Constants
import com.example.prueba_kotlin.R
import com.example.prueba_kotlin.interfaces.OrderSelectListener
import com.example.prueba_kotlin.adapter.AdapterRecycler
import com.example.prueba_kotlin.dao.AllOrders
import com.example.prueba_kotlin.dao.OrderDao
import com.example.prueba_kotlin.dao.database.OrdersDB
import com.example.prueba_kotlin.databinding.ActivityRecallBinding
import com.example.prueba_kotlin.model.CallOrders
import com.example.prueba_kotlin.utils.UtilPopError
import com.example.prueba_kotlin.viewmodels.OrderViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActivityRecall : AppCompatActivity(), OrderSelectListener {


    private lateinit var binding: ActivityRecallBinding
    private val orderViewModel : OrderViewModel  by viewModels()
    private lateinit var adapter: AdapterRecycler
    private lateinit var list : ArrayList<CallOrders>
    private lateinit var filterList : ArrayList<CallOrders>
    private lateinit var orderDao: OrderDao
    private lateinit var ordersList  : ArrayList<AllOrders>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<CallOrders>()
        ordersList = ArrayList()
        orderViewModel.getCallOrder()
        getOrdersCloud()


        binding.btnAll.setOnClickListener {
            adapter.setData(list)
            adapter.notifyDataSetChanged()
        }


        binding.btnDelivery.setOnClickListener(View.OnClickListener {
            filterList = ArrayList<CallOrders>()

            list.forEach{
                if (it.orderType == 4){
                    filterList.add(it)
                }
            }

            adapter.setData(filterList)
            adapter.notifyDataSetChanged()
        })


        binding.btnDine.setOnClickListener {
            filterList = ArrayList<CallOrders>()

            list.forEach{
                if (it.orderType == 2){
                    filterList.add(it)
                }
            }

            adapter.setData(filterList)
            adapter.notifyDataSetChanged()
        }
        binding.btnPickup.setOnClickListener {
            filterList = ArrayList<CallOrders>()

            list.forEach{
                if (it.orderType == 3){
                    filterList.add(it)
                }
            }

            adapter.setData(filterList)
            adapter.notifyDataSetChanged()
        }
        binding.btnTogo.setOnClickListener {
            filterList = ArrayList<CallOrders>()

            list.forEach{
                if (it.orderType == 1){
                    filterList.add(it)
                }
            }

            adapter.setData(filterList)
            adapter!!.notifyDataSetChanged()
        }

    }

    private fun  getOrdersCloud(){
        list.clear()
        val db = Room.databaseBuilder(
            applicationContext,
            OrdersDB::class.java, "orders"
        ).build()

        orderDao = db.ordenDao()

        orderViewModel.callOrder().observe(this, Observer {
            if (it == null) {
                UtilPopError.popError(this, getString(R.string.no_internet), getString(R.string.network_error)) {
                    if (it) getOrdersCloud()
                }
                return@Observer
            }
            list = ArrayList(it)
            adapter = AdapterRecycler(this, ArrayList(list), this@ActivityRecall)


            list.forEach{
                var orders = AllOrders(OrderId = it.orderId, Username = it.username, SubTotal = it.subTotal, TicketNumber = it.ticketNumber, OrderDateTime = it.orderDateTime, OrderType = it.orderType )
                ordersList.add(orders)
            }

            lifecycleScope.launch(Dispatchers.IO){
                orderDao.insert(ordersList)
            }

            binding.recycler.layoutManager = LinearLayoutManager(this)
            binding.recycler.adapter = adapter



        })
    }

    override fun onOrdenSelect(orden: CallOrders) {
        // DetailOrdernActivity.run(this, orden)
        val intent = Intent(applicationContext, DetailsOrders::class.java)
        intent.putExtra(Constants.ORDER, Gson().toJson(orden))
        startActivity(intent)

    }


    override fun onResume() {
        super.onResume()
        // adapter.notifyDataSetChanged()
    }
}