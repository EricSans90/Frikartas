package com.example.frikartas.data.models

data class OnePieceCollectionDTO(
    val name: String,
    val publicationYear: Int,
    val cards: List<OnePieceCardDTO>
)

data class OnePieceCardDTO(
    val name: String,
    val urlImg: String,
    val rarity: String,
    val stock: Int,
    val precio: Float,
    val discount: Boolean,
)