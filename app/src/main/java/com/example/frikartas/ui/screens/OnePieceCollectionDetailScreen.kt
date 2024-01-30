package com.example.frikartas.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.components.OnePieceCollectionDetailView
import com.example.frikartas.ui.viewmodels.OnePieceViewModel

@Composable
fun OnePieceCollectionDetailScreen(navController: NavController, collectionName: String, viewModel: OnePieceViewModel = hiltViewModel()) {
    val collection = viewModel.getCollectionByName(collectionName)
    collection?.let {
        OnePieceCollectionDetailView(onePieceCollection = it, onItemClick = { collectionName, cardName ->
            navController.navigate("onePieceDetail/$collectionName/$cardName")
        })
    } ?: Text(text = "Collection not found")
}