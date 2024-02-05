package com.example.frikartas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frikartas.ui.viewmodels.CardViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frikartas.R
import com.example.frikartas.ui.components.CardListItem
import com.example.frikartas.ui.components.CollectionListItem

@Composable
fun CollectionListScreen(navController: NavController, viewModel: CardViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.mainscreen),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        val collections by viewModel.collections.observeAsState(initial = listOf())

        LazyColumn {
            items(collections) { collection ->
                CollectionListItem(
                    collection = collection,
                    onItemClick = {
                        navController.navigate("collectionDetail/${collection.collectionId}")
                    }
                )
            }
        }
    }
}
