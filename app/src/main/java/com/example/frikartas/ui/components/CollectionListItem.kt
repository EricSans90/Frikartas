package com.example.frikartas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frikartas.R
import com.example.frikartas.domain.models.Collection

@Composable
fun CollectionListItem(collection: Collection, onItemClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = collection.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            )
            Text(text = "Año de publicación: ${collection.publicationYear}",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.krusyidascripttwo)),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}