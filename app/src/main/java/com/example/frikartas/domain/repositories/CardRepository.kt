package com.example.frikartas.domain.repositories

import com.example.frikartas.domain.models.Collection

/**
 * Interfaz que define los métodos para acceder a las colecciones de cartas.
 */
interface CardRepository {
    /**
     * Obtiene todas las colecciones de cartas disponibles.
     *
     * @return Lista de colecciones de cartas.
     */
    suspend fun getCardCollections(): List<Collection>
}