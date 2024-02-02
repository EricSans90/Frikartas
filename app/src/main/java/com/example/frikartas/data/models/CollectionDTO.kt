package com.example.frikartas.data.models

data class CollectionDTO(
    val collectionId: Int,
    val collectType: CollectTypeDTO,
    val name: String,
    val publicationYear: Int,
    val cards: List<CardDTO>
)