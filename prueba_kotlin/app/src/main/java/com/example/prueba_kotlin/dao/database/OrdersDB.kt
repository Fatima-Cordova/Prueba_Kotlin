package com.example.prueba_kotlin.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.prueba_kotlin.dao.AllOrders
import com.example.prueba_kotlin.dao.OrderDao

@Database(entities = [AllOrders::class], version = 1)
abstract class OrdersDB : RoomDatabase() {

    abstract fun ordenDao() : OrderDao

    companion object {
        private var instance: OrdersDB? = null

        @Synchronized
        fun getInstance(ctx: Context): OrdersDB {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, OrdersDB::class.java,
                    "order_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}