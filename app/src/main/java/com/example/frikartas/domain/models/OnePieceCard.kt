package com.example.frikartas.domain.models

data class OnePieceCard (
    val name: String,
    //tags?
    val rarity: String,
    val stock: Int,
    val price: Float,
    val discount: Boolean,
    //De momento discount fijo 20%
    val discountAmount: Float = 20.0F,
    val precioDiscount: Float = if(discount){(100-discountAmount)*price} else price,
    val SKU: String,
    val urlImages: List<String>,
    //description?
    //languages?
    //size?
)