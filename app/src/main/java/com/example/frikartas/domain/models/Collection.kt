package com.example.frikartas.domain.models

import com.example.frikartas.data.models.CollectTypeDTO


data class Collection(
    val collectType: CollectTypeDTO,
    val name: String,
    val publicationYear: Int,
    var cards: List<Card>,
)