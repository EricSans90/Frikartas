package com.example.frikartas.domain.repositories

import com.example.frikartas.domain.models.Collection

interface CardRepository {
    fun getCardCollections(): List<Collection>
}