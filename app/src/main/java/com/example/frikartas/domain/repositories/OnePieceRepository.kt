package com.example.frikartas.domain.repositories

import com.example.frikartas.domain.models.OnePieceCard

interface OnePieceRepository {
    fun getOnePieceCards(): List<OnePieceCard>
}