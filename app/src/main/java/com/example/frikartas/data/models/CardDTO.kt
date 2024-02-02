package com.example.frikartas.data.models

data class CollectionDTO(
    val name: String,
    val publicationYear: Int,
    val cards: List<CardDTO>
)

data class CardDTO(
    val name: String,
    val tags: List<String>,
    val rarity: String,
    val stock: Int,
    val price: Float,
    val discount: Boolean,
    val SKU: String,
    val urlImages: List<String>,
    val description: String,
    val languages: List<LanguageDTO>,
    val size: String
)

