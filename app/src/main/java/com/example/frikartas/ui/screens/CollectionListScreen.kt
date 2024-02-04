package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.viewmodels.CardViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frikartas.ui.components.CardListItem
import com.example.frikartas.ui.components.CollectionListItem

@Composable
fun CollectionListScreen(navController: NavController, viewModel: CardViewModel = hiltViewModel()) {
    val collections by viewModel.collections.observeAsState(initial = listOf())
    LaunchedEffect(key1 = true) {
        viewModel.navigationEvents.collect { navRoute ->
            navController.navigate(navRoute)
        }
    }

    LazyColumn {
        items(collections) { collection ->
            CollectionListItem(
                collection = collection,
                onItemClick = {
                    // Cambio: Navegar usando el ID de la colección en lugar del nombre
                    navController.navigate("collectionDetail/${collection.collectionId}")
                }
            )
        }
    }
}