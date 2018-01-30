package com.example.ms.petrol

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.parse.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBotNav:BottomNavigationView
    private lateinit var mMainFrag:FrameLayout
    private var mAddFrag = AddFragment()
    private var mViewFrag = ViewFragment()
    private var mAboutFrag = AboutFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Parse.initialize(Parse.Configuration.Builder(this).
                applicationId("m4A6Eq9GhmXp6nSNBdEhRZUOvMcsxO23NKl2hf3Q").
                server("https://parseapi.back4app.com/").
                clientKey("rCXV2nCQWZSlwAnvHiGjC9roBgZS4hP4LGhHKbz1").build())

//        ParseUser.logInInBackground("bogus","startv123", LogInCallback({user: ParseUser?, e: ParseException? ->
//            if(user != null){
//                println("Signed In")
//            }else{
//                println(e?.printStackTrace())
//            }
//        }))

        var user = ParseUser.getCurrentUser()
        if(user == null){
            println("not logged in")
        }
        else{
            println("user is logged in please use session callback")
        }
        ParseSession.getCurrentSessionInBackground(GetCallback { `object`,e ->
            if(`object` != null){
                println("Sessin Exists")
            }
            else{
                println(e.printStackTrace())
            }
        })


        setContentView(R.layout.activity_main)
        mytoolbar.setTitle("Petrol Tracker")
        setSupportActionBar(mytoolbar)
        mBotNav = findViewById(R.id.mybotnav)
        mMainFrag = findViewById(R.id.mainfrag)
        replaceFrag(mAddFrag)
        mBotNav.setOnNavigationItemSelectedListener({ item:MenuItem ->
           if(item.itemId == R.id.add){
               replaceFrag(mAddFrag);
           }
           if(item.itemId == R.id.view){
               replaceFrag(mViewFrag)
           }
           if(item.itemId == R.id.about){
               replaceFrag(mAboutFrag)
           }
           true

        })
    }
    

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.myactionbar,menu)
        return super.onCreateOptionsMenu(menu)
    }



    fun replaceFrag(fragment: Fragment){
        var fragManager = getFragmentManager().beginTransaction();
        fragManager.setCustomAnimations(R.animator.enter,R.animator.leave)
        fragManager.replace(R.id.mainfrag,fragment);
        fragManager.commit()
    }


}
