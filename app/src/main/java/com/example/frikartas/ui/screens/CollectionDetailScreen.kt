package com.example.frikartas.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.CollectionDetailView
import com.example.frikartas.ui.viewmodels.CardViewModel

@Composable
fun CollectionDetailScreen(navController: NavController, collectionName: String, viewModel: CardViewModel = hiltViewModel()) {
    val collection = viewModel.getCollectionByName(collectionName)
    collection?.let {
        CollectionDetailView(collection = it, onItemClick = { collectionName, cardName ->
            navController.navigate("onePieceDetail/$collectionName/$cardName")
        })
    } ?: Text(text = "Collection not found")
}