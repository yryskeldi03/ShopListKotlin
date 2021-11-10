package com.example.shoplistkotlin.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplistkotlin.data.ShopListRepositoryImpl
import com.example.shoplistkotlin.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun getItem(id: Int): ShopItem {
        return getShopItemUseCase.getShopItem(id)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItem)
    }

}