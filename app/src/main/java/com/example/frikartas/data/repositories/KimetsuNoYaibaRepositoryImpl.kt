package com.example.frikartas.data.repositories

import com.example.frikartas.data.mappers.mapKimetsuNoYaibaCardDTOToCard
import com.example.frikartas.data.sources.local.KimetsuNoYaibaLocalDataSource
import com.example.frikartas.domain.models.KimetsuNoYaibaCard
import com.example.frikartas.domain.repositories.KimetsuNoYaibaRepository
import javax.inject.Inject


class KimetsuNoYaibaRepositoryImpl @Inject constructor(private val localDataSource: KimetsuNoYaibaLocalDataSource) :
    KimetsuNoYaibaRepository {

    override fun getKimetsuNoYaibaCards(): List<KimetsuNoYaibaCard> {
        val cardDTOs = localDataSource.getKimetsuNoYaibaCards()
        return cardDTOs.map{ mapKimetsuNoYaibaCardDTOToCard(it) }
    }
}