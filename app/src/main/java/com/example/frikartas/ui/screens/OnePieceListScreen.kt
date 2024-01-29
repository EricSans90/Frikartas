package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OnePieceListScreen(onNavigate: (String)-> Unit){
    Column(){
        Text("LISTA DE CARTAS OP")

        //Prueba navegación a detail carta
        Button(onClick = {onNavigate("onePieceDetail")}){
            Text("Ver detail de OP")
        }
    }
}