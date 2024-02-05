package com.example.frikartas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.R
import com.example.frikartas.ui.components.CollectionDetailView
import com.example.frikartas.ui.viewmodels.CardViewModel

@Composable
fun CollectionDetailScreen(navController: NavController, collectionId: Int, viewModel: CardViewModel = hiltViewModel()) {
    val collection = viewModel.getCollectionById(collectionId)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.ui.graphics.Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.secondscreen),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            collection?.let { col ->
                CollectionDetailView(collection = col, onItemClick = { _, cardId ->
                    navController.navigate("cardDetail/$cardId")
                })
            } ?: Text(text = "Collection not found")
        }
    }
}