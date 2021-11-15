package com.example.shoplistkotlin.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoplistkotlin.domain.ShopItem

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_item")
    fun getAll(): LiveData<List<ShopItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shopItem: ShopItem)

    @Update
    fun update(shopItem: ShopItem)

    @Query("SELECT * FROM shop_item WHERE id = :id")
    fun getShopItemById(id: Int): ShopItem

    @Delete
    fun delete(shopItem: ShopItem)

}