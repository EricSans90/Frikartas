package com.example.frikartas.domain.models


data class Collection (
    val name: String,
    val publicationYear: Int,
    var cards: List<Card>
)