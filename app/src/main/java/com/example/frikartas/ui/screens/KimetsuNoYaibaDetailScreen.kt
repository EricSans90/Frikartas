package com.example.frikartas.ui.screens


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.KimetsuNoYaibaDetailView
import com.example.frikartas.ui.viewmodels.KimetsuNoYaibaViewModel

@Composable
fun KimetsuNoYaibaDetailScreen(navController: NavController, cardName: String, viewModel: KimetsuNoYaibaViewModel = hiltViewModel()) {
    val card = viewModel.getCardByName(cardName)
    card?.let {
        KimetsuNoYaibaDetailView(kimetsuNoYaibaCard = it)
    } ?: Text(text = "Card not found")
}