package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.CardListItem
import com.example.frikartas.ui.components.CollectionDetailView
import com.example.frikartas.ui.viewmodels.CardViewModel

@Composable
fun CollectionDetailScreen(navController: NavController, collectionId: Int, viewModel: CardViewModel = hiltViewModel()) {
    val collection = viewModel.getCollectionById(collectionId)
    collection?.let { col ->
        CollectionDetailView(collection = col, onItemClick = { _, cardId ->
            navController.navigate("cardDetail/$cardId")
        })
    } ?: Text(text = "Collection not found")
}