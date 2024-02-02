package com.example.frikartas.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.viewmodels.CardViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.frikartas.ui.components.CardCollectionListItem

@Composable
fun CardListScreen(navController: NavController, viewModel: CardViewModel = hiltViewModel()) {
    val onePieceCollections by viewModel.collections.observeAsState(initial = listOf())

    // Observar eventos de navegación del ViewModel
    LaunchedEffect(key1 = true) {
        viewModel.navigationEvents.collect { navRoute ->
            navController.navigate(navRoute)
        }
    }

    LazyColumn {
        items(onePieceCollections) { collection ->
            CardCollectionListItem(
                collection = collection,
                onItemClick = {
                    // Informar al ViewModel que se ha seleccionado una colección
                    viewModel.onCollectionSelected(collection.name)
                }
            )
        }
    }
}