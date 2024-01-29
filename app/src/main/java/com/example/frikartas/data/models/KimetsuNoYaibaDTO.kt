package com.example.frikartas.data.models

data class KimetsuNoYaibaCollectionDTO(
    val name: String,
    val publicationYear: Int,
    val cards: List<KimetsuNoYaibaCardDTO>
)

data class KimetsuNoYaibaCardDTO(
    val name: String,
    val urlImg: String,
    val rarity: String,
    val stock: Int,
    val precio: Float,
    val discount: Boolean,
)