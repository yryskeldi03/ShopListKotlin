package com.example.shoplistkotlin.data

import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.domain.ShopItemDbModel

class ShopListMapper {

    fun mapEntityToDBModel(shopItem: ShopItem) = ShopItemDbModel(

        name = shopItem.name,
        count = shopItem.count,
        enable = shopItem.enable
    )

    fun mapDbModelToDbEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enable = shopItemDbModel.enable
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToDbEntity(it)
    }

}