package com.example.frikartas.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.domain.models.KimetsuNoYaibaCard
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.ui.viewmodels.KimetsuNoYaibaViewModel
import com.example.frikartas.ui.viewmodels.OnePieceViewModel

@Composable
fun KimetsuNoYaibaListScreen(navController: NavController, viewModel: KimetsuNoYaibaViewModel = hiltViewModel()) {
    val kimetsuNoYaibaCards by viewModel.cards.observeAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(kimetsuNoYaibaCards) { card ->
            Text(
                text = card.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Asigno un nombre de ruta y paso el nombre de la carta
                        navController.navigate("kimetsuNoYaibaDetail/${card.name}")
                    }
                    .padding(8.dp)
            )
        }
    }
}