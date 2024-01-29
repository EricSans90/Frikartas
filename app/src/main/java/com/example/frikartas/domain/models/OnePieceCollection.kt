package com.example.frikartas.domain.models

data class OnePieceCollection (
    val name: String,
    val publicationYear: Int,
    val cards: List<OnePieceCard>
)