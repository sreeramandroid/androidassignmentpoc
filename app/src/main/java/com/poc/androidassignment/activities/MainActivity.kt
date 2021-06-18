package com.poc.androidassignment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.poc.androidassignment.R
import com.poc.androidassignment.databinding.ActivityMainBinding
import com.poc.androidassignment.fragments.LiveDataFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val firstFragment = LiveDataFragment()
        initFragmentData(firstFragment)
        with(binding) {
            lifecycleOwner = this@MainActivity
        }
    }



    /**
     * function for replace fragment
     * ****/
    private fun initFragmentData(firstFragment: LiveDataFragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, firstFragment)
                commit()
            }
    }
}