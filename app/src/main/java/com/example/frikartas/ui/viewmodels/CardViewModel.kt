package com.example.frikartas.ui.viewmodels

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frikartas.domain.models.Card
import com.example.frikartas.domain.models.Collection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.asFlow
import com.example.frikartas.domain.usecases.GetCardDataUseCase


/**
 * ViewModel que maneja la lógica de la UI relacionada con las cartas y colecciones de cartas.
 *
 * Este ViewModel trabaja con [GetCardDataUseCase] para obtener los datos necesarios y
 * proporcionarlos a la UI. Mantiene el estado de las colecciones y las cartas, maneja la lógica
 * de la selección de cartas favoritas, y provee métodos para interactuar con los datos.
 *
 * @property getCardDataUseCase Caso de uso para obtener las colecciones de cartas.
 */
@HiltViewModel
class CardViewModel @Inject constructor(private val getCardDataUseCase: GetCardDataUseCase) : ViewModel() {
    val collections = MutableLiveData<List<Collection>>()
    val cards = MutableLiveData<List<Card>>()
    val cardsFlow = cards.asFlow()

    //algunos puede que no los use pero los dejo para cuando amplie el proyecto quizás los necesite

    init {
        loadCollections()
    }

    /**
     * Carga las colecciones de cartas al iniciar el ViewModel.
     *
     * Utiliza [GetCardDataUseCase] para obtener las colecciones de cartas
     * y actualizar el LiveData correspondiente.
     */
    private fun loadCollections() {
        viewModelScope.launch {
            collections.value = getCardDataUseCase.execute()
        }
    }

    /**
     * Carga las cartas de una colección específica.
     *
     * Busca en el LiveData de colecciones la colección con el ID proporcionado y
     * actualiza el LiveData de cartas con las cartas de la colección encontrada.
     *
     * @param collectionId El ID de la colección cuyas cartas se quieren cargar.
     */
    fun loadCardsFromCollection(collectionId: Int) {
        viewModelScope.launch {
            collections.value?.find { it.collectionId == collectionId }?.let { collection ->
                cards.value = collection.cards
            }
        }
    }

    /**
     * Obtiene una colección por nombre.
     *
     * @param name El nombre de la colección.
     * @return La colección si se encuentra, null en caso contrario.
     */
    fun getCollectionByName(name: String): Collection? {
        val normalizedName = name.trim().toLowerCase()
        return collections.value?.find { it.name.trim().toLowerCase() == normalizedName }
    }

    /**
     * Obtiene una carta de una colección específica por nombre.
     *
     * @param collectionName El nombre de la colección.
     * @param cardName El nombre de la carta.
     * @return La carta si se encuentra, null en caso contrario.
     */
    fun getCardFromCollection(collectionName: String, cardName: String): Card? {
        val collection = collections.value?.find { it.name.trim().toLowerCase() == collectionName.trim().toLowerCase() }
        return collection?.cards?.find { it.name.trim().toLowerCase() == cardName.trim().toLowerCase() }
    }

    private val _navigationEvents = MutableSharedFlow<String>()
    val navigationEvents = _navigationEvents.asSharedFlow()


    /**
     * Obtiene una carta por ID.
     *
     * @param cardId El ID de la carta.
     * @return La carta si se encuentra, null en caso contrario.
     */
    fun getCardById(cardId: Int): Card? {
        return collections.value?.flatMap { it.cards }?.find { it.cardId == cardId }
    }

    /**
     * Obtiene una colección por ID.
     *
     * @param collectionId El ID de la colección.
     * @return La colección si se encuentra, null en caso contrario.
     */
    fun getCollectionById(collectionId: Int): Collection? {
        return collections.value?.find { it.collectionId == collectionId }
    }

    /**
     * Obtiene una colección al azar.
     *
     * @return El ID de la colección si hay colecciones disponibles, null en caso contrario.
     */
    fun getRandomCollectionId(): Int? {
        val currentCollections = collections.value
        if (!currentCollections.isNullOrEmpty()) {
            val randomIndex = (currentCollections.indices).random()
            return currentCollections[randomIndex].collectionId
        }
        return null  // Devuelve null si no hay colecciones
    }

    /**
     * Obtiene una carta al azar.
     *
     * @return El ID de la carta si hay cartas disponibles, null en caso contrario.
     */
    fun getRandomCardId(): Int? {
        // usando todas las cartas de todas las colecciones para esto
        val allCards = collections.value?.flatMap { it.cards }
        if (!allCards.isNullOrEmpty()) {
            val randomIndex = (allCards.indices).random()
            return allCards[randomIndex].cardId
        }
        return null  // Devuelve null si no hay cartas
    }

    /**
     * Obtiene una carta al azar que tiene descuento.
     *
     * @return El ID de la carta con descuento si hay cartas con descuento disponibles, null en caso contrario.
     */
    fun getRandomDiscountedCardId(): Int? {
        // Filtra todas las cartas para obtener solo aquellas con descuento
        val discountedCards = collections.value
            ?.flatMap { it.cards }
            ?.filter { it.discount }
        if (!discountedCards.isNullOrEmpty()) {
            val randomIndex = (discountedCards.indices).random()
            return discountedCards[randomIndex].cardId
        }
        return null  // Devuelve null si no hay cartas con descuento
    }

    // Mapa para manejar el estado de favoritos temporalmente hasta que lo implemente en la BD
    var favoriteCards = mutableStateMapOf<Int, Boolean>()
        private set

    /**
     * Cambia el estado de favorito de una carta.
     *
     * Si la carta ya está marcada como favorita, la desmarca, y viceversa.
     *
     * @param cardId El ID de la carta cuyo estado de favorito se quiere cambiar.
     */
    fun toggleFavorite(cardId: Int) {
        val currentFavoriteStatus = favoriteCards[cardId] ?: false
        favoriteCards[cardId] = !currentFavoriteStatus
    }

    /**
     * Verifica si una carta está marcada como favorita.
     *
     * @param cardId El ID de la carta.
     * @return true si la carta está marcada como favorita, false en caso contrario.
     */
    fun isCardFavorite(cardId: Int) = favoriteCards[cardId] ?: false
}