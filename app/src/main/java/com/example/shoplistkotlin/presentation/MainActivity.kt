package com.example.shoplistkotlin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.shoplistkotlin.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.shopList.observe(this){
            Log.e("MainActivityTest", it.toString())
        }

        viewModel.deleteShopItem(viewModel.getItem(8))

        viewModel.changeEnableState(viewModel.getItem(6))
    }
}