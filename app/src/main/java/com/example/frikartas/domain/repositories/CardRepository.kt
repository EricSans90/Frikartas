package com.example.frikartas.domain.repositories

import com.example.frikartas.domain.models.Collection

interface CardRepository {
    suspend fun getCardCollections(): List<Collection>
}