package com.example.frikartas.data.repositories

import com.example.frikartas.data.sources.local.CardLocalDataSource
import com.example.frikartas.domain.repositories.CardRepository
import com.example.frikartas.data.mappers.mapOnePieceCollectionDTOToCollection
import com.example.frikartas.domain.models.Collection
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val localDataSource: CardLocalDataSource) :
    CardRepository {

    override fun getCardCollections(): List<Collection> {
        val collectionDTOs = localDataSource.getOnePieceCollections()
        return collectionDTOs.map { mapOnePieceCollectionDTOToCollection(it) }
    }
}