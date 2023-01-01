package com.athtech.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.athtech.moviesapp.recycler.RecyclerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        var intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)

//        var button =findViewById<Button>(R.id.btn_test)
//        button.setOnClickListener{
//            var intent = Intent(this, RecyclerActivity::class.java)
//          //  var intent = Intent(this, ServerActivity::class.java)
//
//            startActivity(intent)
//        }




    }




}