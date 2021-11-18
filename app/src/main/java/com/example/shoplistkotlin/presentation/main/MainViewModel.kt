package com.example.shoplistkotlin.presentation.main

import androidx.lifecycle.ViewModel
import com.example.shoplistkotlin.ShopListApp
import com.example.shoplistkotlin.data.ShopListRepositoryImpl
import com.example.shoplistkotlin.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl(ShopListApp.shopDatabase.shopDao())

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val addShopListUseCase = AddShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun addItem(shopItem: ShopItem) {
        addShopListUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun editItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(name = shopItem.name, count = shopItem.count)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun getItem(id: Int): ShopItem {
        return getShopItemUseCase.getShopItem(id)
    }

}