package com.example.frikartas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frikartas.R

/**
 * Pantalla principal de la aplicación. Muestra una imagen de fondo y proporciona un botón para navegar a la lista de colecciones.
 *
 * @param onNavigate Función que se invoca para navegar a la lista de colecciones.
 */
@Composable
fun MainScreen(onNavigate: (String)-> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.Gray)
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.mainscreen),
            contentDescription = "Fondo de pantalla principal",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Botón que nos lleva a las colecciones
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp)
        ) {
            Button(onClick = { onNavigate("collectionList") }) {
                Text("Ver lista de colecciones")
            }
        }
    }
}