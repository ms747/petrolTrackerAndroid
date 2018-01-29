package com.example.ms.petrol

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.parse.ParseObject


class CustomAdapter(pData:List<ParseObject?>): RecyclerView.Adapter<CustomAdapterViewHolder>(){

    lateinit var mydata:List<ParseObject?>

    init {
        mydata = pData
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomAdapterViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerlay,parent,false)
        return CustomAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mydata.size

    }

    override fun onBindViewHolder(holder: CustomAdapterViewHolder?, position: Int) {
        holder?.rAge?.text = mydata[position]?.get("age").toString()
        holder?.rName?.text = mydata[position]?.get("name").toString()
    }




}

class CustomAdapterViewHolder(var myview:View) : RecyclerView.ViewHolder(myview){
    lateinit var rName:TextView
    lateinit var rAge:TextView
    init {
        rName = myview.findViewById(R.id.rName)
        rAge = myview.findViewById(R.id.rAge)
    }
}