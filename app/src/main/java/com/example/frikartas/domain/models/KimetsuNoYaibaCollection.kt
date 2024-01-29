package com.example.frikartas.domain.models

data class KimetsuNoYaibaCollection (
    val name: String,
    val publicationYear: Int,
    val cards: List<KimetsuNoYaibaCard>
)