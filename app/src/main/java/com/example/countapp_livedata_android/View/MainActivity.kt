package com.example.countapp_livedata_android.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countapp_livedata_android.R
import com.example.countapp_livedata_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}