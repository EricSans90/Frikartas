package com.example.frikartas.domain.repositories

import com.example.frikartas.domain.models.OnePieceCollection

interface OnePieceRepository {
    fun getOnePieceCollections(): List<OnePieceCollection>
}