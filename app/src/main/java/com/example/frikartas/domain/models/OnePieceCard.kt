package com.example.frikartas.domain.models

data class OnePieceCard (
    val name: String,
    val urlImg: String,
    val rarity: String,
    val stock: Int,
    val precio: Float,
    val discount: Boolean,
    //De momento discount fijo 20%
    val discountAmount: Float = 20.0F,
    val precioDiscount: Any = if(discount){(100-discountAmount)*precio} else precio,
)