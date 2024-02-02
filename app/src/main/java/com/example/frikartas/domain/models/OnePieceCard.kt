package com.example.frikartas.domain.models

import com.example.frikartas.data.models.LanguageDTO


data class OnePieceCard (
    val name: String,
    val tags: List<String>,
    val rarity: String,
    val stock: Int,
    val price: Float,
    val discount: Boolean,
    //De momento discount fijo 20%
    val discountAmount: Float = 20.0F,
    val precioDiscount: Float = if(discount){(100-discountAmount)*price} else price,
    val SKU: String,
    val urlImages: List<String>,
    val description: String,
    val languages: List<LanguageDTO>,
    val size: String,
    val collection: OnePieceCollection
)

