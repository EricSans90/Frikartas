package com.example.frikartas.domain.repositories

import com.example.frikartas.domain.models.KimetsuNoYaibaCard

interface KimetsuNoYaibaRepository {
    fun getKimetsuNoYaibaCards(): List<KimetsuNoYaibaCard>
}