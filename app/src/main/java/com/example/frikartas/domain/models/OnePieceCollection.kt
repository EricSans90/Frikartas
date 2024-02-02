package com.example.frikartas.domain.models


data class OnePieceCollection (
    val name: String,

    val publicationYear: Int,

    var cards: List<OnePieceCard>
)