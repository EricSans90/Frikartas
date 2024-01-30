package com.example.frikartas.data.repositories

import com.example.frikartas.data.sources.local.OnePieceLocalDataSource
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.repositories.OnePieceRepository
import com.example.frikartas.data.mappers.mapOnePieceCardDTOToCard
import com.example.frikartas.data.mappers.mapOnePieceCollectionDTOToCollection
import com.example.frikartas.domain.models.OnePieceCollection
import javax.inject.Inject

class OnePieceRepositoryImpl @Inject constructor(private val localDataSource: OnePieceLocalDataSource) :
    OnePieceRepository {

    override fun getOnePieceCollections(): List<OnePieceCollection> {
        val collectionDTOs = localDataSource.getOnePieceCollections()
        return collectionDTOs.map { mapOnePieceCollectionDTOToCollection(it) }
    }
}