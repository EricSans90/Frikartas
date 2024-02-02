package com.example.frikartas.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.CardDetailView
import com.example.frikartas.ui.viewmodels.CardViewModel

@Composable
fun CardDetailScreen(navController: NavController, collectionName: String, cardName: String, viewModel: CardViewModel = hiltViewModel()) {
    val card = viewModel.getCardFromCollection(collectionName, cardName)
    card?.let {
        CardDetailView(card = it)
    } ?: Text(text = "Card not found")
}