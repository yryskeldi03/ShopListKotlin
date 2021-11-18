package com.example.shoplistkotlin.domain

data class ShopItem(
    val id: Int? = null,
    val name: String,
    val count: Int,
    val enable: Boolean
)