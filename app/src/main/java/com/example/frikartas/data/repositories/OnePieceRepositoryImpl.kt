package com.example.frikartas.data.repositories

import com.example.frikartas.data.sources.local.OnePieceLocalDataSource
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.repositories.OnePieceRepository
import com.example.frikartas.data.mappers.mapOnePieceCardDTOToCard

class OnePieceRepositoryImpl (private val localDataSource: OnePieceLocalDataSource) :
    OnePieceRepository {

    override fun getOnePieceCards(): List<OnePieceCard> {
        val cardDTOs = localDataSource.getOnePieceCards()
        return cardDTOs.map{ mapOnePieceCardDTOToCard(it) }
    }
}