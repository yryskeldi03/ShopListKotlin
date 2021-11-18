package com.example.shoplistkotlin.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.domain.ShopItemDbModel

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_item")
    fun getAll(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shopItemDB: ShopItemDbModel)

    @Delete
    fun delete(shopItemDB: ShopItemDbModel)

    @Query("SELECT * FROM shop_item WHERE id = :id LIMIT 1")
    fun getShopItemById(id: Int): ShopItemDbModel

    @Update
    fun update(shopItemDB: ShopItemDbModel)

}