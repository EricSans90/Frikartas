package com.example.frikartas.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.viewmodels.OnePieceViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun OnePieceListScreen(navController: NavController, viewModel: OnePieceViewModel = hiltViewModel()) {
    val onePieceCards by viewModel.cards.observeAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(onePieceCards) { card ->
            Text(
                text = card.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Asigno un nombre de ruta y paso el nombre de la carta
                        navController.navigate("onePieceDetail/${card.name}")
                    }
                    .padding(8.dp)
            )
        }
    }
}