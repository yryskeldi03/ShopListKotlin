package com.example.shoplistkotlin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoplistkotlin.domain.ShopItem

@Database(entities = [ShopItem::class], version = 1)
abstract class ShopDataBase : RoomDatabase() {

    abstract fun shopDao(): ShopDao

}