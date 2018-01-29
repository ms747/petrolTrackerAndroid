package com.example.ms.petrol


import android.os.Bundle
import android.app.Fragment
import android.support.design.widget.Snackbar
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.parse.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.mysnackbar.*


/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater!!.inflate(R.layout.fragment_blank, container, false)
        var button:AppCompatButton = view.findViewById(R.id.addButton)
        var name:AppCompatEditText = view.findViewById(R.id.name)
        var age:AppCompatEditText = view.findViewById(R.id.age)

        button.setOnClickListener {

            if(ParseUser.getCurrentUser() != null){
                var data = ParseObject("people")
                data.put("name",name.text.toString())
                data.put("age",age.text.toString())
                data.saveInBackground(SaveCallback { e: ParseException? ->
                    if (e == null){
                        Toast.makeText(view.context,"Added",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(view.context,"Error",Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
        return view;




    }

}// Required empty public constructor
