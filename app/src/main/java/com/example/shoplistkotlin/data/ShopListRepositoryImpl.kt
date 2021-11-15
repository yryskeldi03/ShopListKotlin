package com.example.shoplistkotlin.data

import androidx.lifecycle.LiveData
import com.example.shoplistkotlin.data.room.ShopDao
import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.domain.ShopListRepository

class ShopListRepositoryImpl(private val dao: ShopDao) : ShopListRepository {

    override fun addShopItem(shopItem: ShopItem) {
        dao.insert(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        dao.delete(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        dao.update(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return dao.getShopItemById(shopItemId)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return dao.getAll()
    }
}