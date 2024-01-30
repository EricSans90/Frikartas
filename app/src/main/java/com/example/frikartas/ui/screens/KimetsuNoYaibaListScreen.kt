package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.KimetsuNoYaibaListItem
import com.example.frikartas.ui.viewmodels.KimetsuNoYaibaViewModel

@Composable
fun KimetsuNoYaibaListScreen(navController: NavController, viewModel: KimetsuNoYaibaViewModel = hiltViewModel()) {
    val kimetsuNoYaibaCards by viewModel.cards.observeAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(kimetsuNoYaibaCards) { card ->
            KimetsuNoYaibaListItem(
                kimetsuNoYaibaCard = card,
                onItemClick = {
                    navController.navigate("kimetsuNoYaibaDetail/${card.name}")
                }
            )
        }
    }
}