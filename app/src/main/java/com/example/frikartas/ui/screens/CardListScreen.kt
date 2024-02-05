package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.CardListItem
import com.example.frikartas.ui.viewmodels.CardViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle

/**
 * Pantalla que muestra una lista de cartas pertenecientes a una colección específica.
 *
 * @param navController Controlador de navegación para manejar la navegación entre pantallas.
 * @param collectionId Identificador de la colección cuyas cartas se quieren mostrar.
 * @param viewModel ViewModel que provee los datos y operaciones relacionados con las cartas.
 */
@Composable
fun CardListScreen(
    navController: NavController,
    collectionId: Int,
    viewModel: CardViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current
        val cardsFlowLifecycleAware = remember(viewModel.cardsFlow, lifecycleOwner) {
            viewModel.cardsFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        }
        val cards by cardsFlowLifecycleAware.collectAsState(initial = listOf())

        LaunchedEffect(key1 = collectionId) {
            viewModel.loadCardsFromCollection(collectionId)
        }

        LazyColumn {
            items(cards) { card ->
                CardListItem(
                    card = card,
                    onItemClick = { cardId ->
                        navController.navigate("cardDetail/$cardId")
                    }
                )
            }
        }
    }
}