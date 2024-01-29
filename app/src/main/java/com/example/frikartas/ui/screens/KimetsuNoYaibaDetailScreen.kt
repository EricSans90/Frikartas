package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.viewmodels.KimetsuNoYaibaViewModel

@Composable
fun KimetsuNoYaibaDetailScreen(navController: NavController, cardName: String, viewModel: KimetsuNoYaibaViewModel = hiltViewModel()) {
    val card = viewModel.getCardByName(cardName)

    Column(modifier = Modifier.padding(16.dp)) {
        card?.let {
            Text(text = "Name: ${card.name}")
            Text(text = "Rarity: ${card.rarity}")
            // otros detalles de la carta
        } ?: Text(text = "Card not found")
    }
}