package com.example.frikartas.ui.screens

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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.frikartas.ui.components.CardListItem


@Composable
fun CardListScreen(
    navController: NavController,
    collectionId: Int,
    viewModel: CardViewModel = hiltViewModel()
) {
    // Observa las cartas de la colección específica
    val lifecycleOwner = LocalLifecycleOwner.current
    val cardsFlowLifecycleAware = remember(viewModel.cardsFlow, lifecycleOwner) {
        viewModel.cardsFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val cards by cardsFlowLifecycleAware.collectAsState(initial = listOf())

    // Carga las cartas cuando la pantalla se compone
    LaunchedEffect(key1 = collectionId) {
        viewModel.loadCardsFromCollection(collectionId)
    }

    LazyColumn {
        items(cards) { card ->
            CardListItem(
                card = card,
                onItemClick = { cardId ->
                    // Navega a los detalles de la carta seleccionada
                    navController.navigate("cardDetail/$cardId")
                }
            )
        }
    }
}