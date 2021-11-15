package com.example.shoplistkotlin.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_item")
data class ShopItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = UNDEFINED_ID,
    val name: String,
    val count: Int,
    val enable: Boolean
)
{
    companion object {
        const val UNDEFINED_ID = 0
    }
}