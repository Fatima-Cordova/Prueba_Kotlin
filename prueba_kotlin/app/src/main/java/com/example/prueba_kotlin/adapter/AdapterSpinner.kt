package com.example.prueba_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.prueba_kotlin.R

class AdapterSpinner (internal var context: Context, internal var types: Array<String>) :
    BaseAdapter() {
    internal var inflter: LayoutInflater

    init {
        inflter = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return types.size
    }


    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {

        val view = inflter.inflate(R.layout.items_spinner,null)
        val names = view.findViewById<View>(R.id.textView) as TextView?
        names!!.text = types[i]
        return view
    }
}