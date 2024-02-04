package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(onNavigate: (String)-> Unit){
    Column(Modifier.fillMaxSize()){
        Text("Bienvenido a Frikartas")
        Button(onClick = { onNavigate("onePieceList") }) {
            Text("Ver lista de colecciones de One Piece")
        }
    }
}