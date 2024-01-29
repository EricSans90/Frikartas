package com.example.frikartas.domain.models

data class KimetsuNoYaibaCard (
    val name: String,
    val urlImg: String,
    val rarity: String,
    val stock: Int,
    val precio: Float,
    val discount: Boolean,
    //De momento discount fijo 15%
    val discountAmount: Float = 15.0F,
    val precioDiscount: Any = if(discount){(100-discountAmount)*precio} else precio,
)