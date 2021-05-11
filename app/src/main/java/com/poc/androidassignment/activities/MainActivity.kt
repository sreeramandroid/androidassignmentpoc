package com.poc.androidassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.poc.androidassignment.R
import com.poc.androidassignment.databinding.ActivityMainBinding
import com.poc.androidassignment.fragments.LiveDataFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val firstFragment = LiveDataFragment()
        initFramgmentData(firstFragment)
        //initNullReferences()
        with(binding) {
            lifecycleOwner = this@MainActivity
        }
    }


    /**
     * function for replace fragment
     * ****/
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun initFramgmentData(firstFragment: LiveDataFragment) {
        replaceFragment(firstFragment)
    }
}