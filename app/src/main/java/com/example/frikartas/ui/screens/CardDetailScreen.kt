package com.example.frikartas.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.CardDetailView
import com.example.frikartas.ui.viewmodels.CardViewModel

/**
 * Pantalla de detalles de la carta. Muestra los detalles de una carta específica, permitiendo al usuario marcarla como favorita.
 *
 * @param navController Controlador de navegación para manejar la navegación entre pantallas.
 * @param cardId Identificador de la carta cuyos detalles se quieren mostrar.
 * @param viewModel ViewModel que provee los datos y operaciones relacionados con las cartas.
 */
@Composable
fun CardDetailScreen(navController: NavController, cardId: Int, viewModel: CardViewModel = hiltViewModel()) {
    val card = viewModel.getCardById(cardId)
    val isFavorite = viewModel.isCardFavorite(cardId)

    card?.let {currentCard ->
        // Mostrar el detalle de la carta
        CardDetailView(
            card = currentCard,
            isFavorite = isFavorite,
            onFavoriteChange = { viewModel.toggleFavorite(cardId) },
            onItemClick = { _, cardId ->
                // Aquí manejo lo que sucede cuando se hace clic en un elemento
            }
        )
    } ?: Text(text = "Card not found")
}