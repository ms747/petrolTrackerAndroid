package com.example.ms.petrol

import android.app.Fragment
import android.content.Intent
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



//        ParseUser.logInInBackground("bogus","startv123", LogInCallback({user: ParseUser?, e: ParseException? ->
//            if(user != null){
//                println("Signed In")
//            }else{
//                println(e?.printStackTrace())
//            }
//        }))

//        var user = ParseUser.getCurrentUser()
//        if(user == null){
//            println("not logged in")
//        }
//        else{
//            println("user is logged in please use session callback")
//        }
//        ParseSession.getCurrentSessionInBackground(GetCallback { `object`,e ->
//            if(`object` != null){
//                println("Sessin Exists")
//            }
//            else{
//                println(e.printStackTrace())
//            }
//        })


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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }



    fun replaceFrag(fragment: Fragment){
        var fragManager = getFragmentManager().beginTransaction();
        fragManager.setCustomAnimations(R.animator.enter,R.animator.leave)
        fragManager.replace(R.id.mainfrag,fragment);
        fragManager.commit()
    }


    fun logout(){
        ParseUser.logOutInBackground(LogOutCallback {
            var loginIntent = Intent(this,loginActivity::class.java)
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(loginIntent)
        })
    }


}
