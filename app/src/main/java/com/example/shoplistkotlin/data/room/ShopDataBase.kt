package com.example.shoplistkotlin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.domain.ShopItemDbModel

@Database(entities = [ShopItemDbModel::class], version = 3, exportSchema = false)
abstract class ShopDataBase : RoomDatabase() {

    abstract fun shopDao(): ShopDao

}