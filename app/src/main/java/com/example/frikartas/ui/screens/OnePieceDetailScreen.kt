package com.example.frikartas.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.OnePieceCollectionDetailView
import com.example.frikartas.ui.components.OnePieceDetailView
import com.example.frikartas.ui.viewmodels.OnePieceViewModel

@Composable
fun OnePieceDetailScreen(navController: NavController, collectionName: String, cardName: String, viewModel: OnePieceViewModel = hiltViewModel()) {
    val card = viewModel.getCardFromCollection(collectionName, cardName)
    card?.let {
        OnePieceDetailView(onePieceCard = it)
    } ?: Text(text = "Card not found")
}