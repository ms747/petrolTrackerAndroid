package com.example.ms.petrol

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.parse.LogInCallback
import com.parse.Parse
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {

    lateinit var mProgressBar:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mProgressBar = ProgressDialog(this)

        checkUserLogin()

        loginButton.setOnClickListener {
            login()
        }

    }

    fun checkUserLogin(){

        Parse.initialize(Parse.Configuration.Builder(this).
                applicationId("m4A6Eq9GhmXp6nSNBdEhRZUOvMcsxO23NKl2hf3Q").
                server("https://parseapi.back4app.com/").
                clientKey("rCXV2nCQWZSlwAnvHiGjC9roBgZS4hP4LGhHKbz1").build())

        var user = ParseUser.getCurrentUser()
        if(user != null){
            println("logged in")
            var mainIntent = Intent(this,MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(mainIntent)
        }
        else{
            println("logged out")
        }
    }

    fun login(){
        var userName = username.text.toString().trim()
        var password = passwordInput.text.toString().trim()

        mProgressBar.setMessage("Logging In")
        mProgressBar.show()
        ParseUser.logInInBackground(userName,password, LogInCallback { user, e ->
            if(user != null){
                println("logged in via inputs")
                mProgressBar.dismiss()
                checkUserLogin()
            }
            else{
                println(e.printStackTrace())
            }
        })
    }
}
