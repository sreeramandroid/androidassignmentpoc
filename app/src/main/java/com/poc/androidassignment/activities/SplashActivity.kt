package com.poc.androidassignment.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.poc.androidassignment.R

class SplashActivity : AppCompatActivity() { override fun onCreate(savedInstanceState: Bundle?) {
 super.onCreate(savedInstanceState)
 setContentView(R.layout.activity_splash)

 /****
 * Logic for splash
 * ******/

 Handler(Looper.getMainLooper()).postDelayed({
 val i = Intent(this@SplashActivity, MainActivity::class.java)
 startActivity(i)
 finish()
 }, 3000)
 }
}