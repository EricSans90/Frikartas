package com.example.frikartas.domain.models

data class KimetsuNoYaibaCard (
    val name: String,
    //tags?
    val rarity: String,
    val stock: Int,
    val price: Float,
    val discount: Boolean,
    //De momento discount fijo 15%
    val discountAmount: Float = 15.0F,
    val precioDiscount: Float = if(discount){(100-discountAmount)*price} else price,
    val SKU: String,
    val urlImages: List<String>,
    //description?
    //languages?
    //size?
)