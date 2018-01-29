package com.example.ms.petrol


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_view.*
import kotlinx.android.synthetic.main.fragment_view.view.*


/**
 * A simple [Fragment] subclass.
 */
class ViewFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater!!.inflate(R.layout.fragment_view, container, false)
        var pQuery = ParseQuery.getQuery<ParseObject>("people")




        view.mylist.layoutManager = LinearLayoutManager(view.context)
        pQuery.findInBackground(FindCallback { objects, e ->
            view.mylist.adapter = CustomAdapter(objects)
        })



        return view;
    }

}// Required empty public constructor
