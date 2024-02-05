package com.example.frikartas.domain.usecases

import com.example.frikartas.domain.repositories.CardRepository
import com.example.frikartas.domain.models.Collection
import javax.inject.Inject

/**
 * Use case para obtener todas las colecciones de cartas.
 *
 * Esta clase encapsula la lógica de negocio para obtener las colecciones de cartas.
 * Delega la obtención de los datos al repositorio de cartas.
 *
 * @property cardRepository Repositorio que será utilizado para obtener las colecciones de cartas.
 */
class GetCardDataUseCase @Inject constructor(private val cardRepository: CardRepository) {
    /**
     * Obtiene todas las colecciones de cartas desde el repositorio.
     *
     * Este método ejecuta la llamada para obtener las colecciones de cartas desde el repositorio.
     * La operación es suspendida, lo que significa que debe ser llamada desde una coroutina
     * o desde otra función suspendida.
     *
     * @return Una lista de objetos de dominio [Collection] que representa las colecciones de cartas.
     * @throws Exception si hay algún problema durante la obtención de las colecciones.
     */
    suspend fun execute(): List<Collection> {
        return cardRepository.getCardCollections()
    }
}