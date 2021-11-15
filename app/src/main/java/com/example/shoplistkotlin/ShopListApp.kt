package com.example.shoplistkotlin

import android.app.Application
import androidx.room.Room
import com.example.shoplistkotlin.data.room.ShopDataBase

class ShopListApp : Application() {

    companion object {
        lateinit var shopDatabase: ShopDataBase
    }

    override fun onCreate() {
        super.onCreate()
        shopDatabase = Room.databaseBuilder(this, ShopDataBase::class.java, "database")
            .allowMainThreadQueries().build()
    }

}