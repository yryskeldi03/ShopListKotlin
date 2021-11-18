package com.example.shoplistkotlin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoplistkotlin.data.room.ShopDao
import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.domain.ShopListRepository

class ShopListRepositoryImpl(private val dao: ShopDao) : ShopListRepository {
    private val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        dao.insert(mapper.mapEntityToDBModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        dao.delete(mapper.mapEntityToDBModel(shopItem))
    }

    override fun editShopItem(shopItem: ShopItem) {
        dao.update(mapper.mapEntityToDBModel(shopItem))
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return mapper.mapDbModelToDbEntity(dao.getShopItemById(shopItemId))
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        dao.getAll()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}