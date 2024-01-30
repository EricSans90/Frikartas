package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(onNavigate: (String)-> Unit){
    Column(){
        Text("Bienvenido a Frikartas")

        Button(onClick = {onNavigate("kimetsuNoYaibaList")}){
            Text("Ver lista de KNY")
        }

        Text("Bienvenido a Frikartas")
        Button(onClick = { onNavigate("onePieceList") }) {
            Text("Ver lista de colecciones de One Piece")
        }
    }
}