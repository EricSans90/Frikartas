package com.example.frikartas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun KimetsuNoYaibaListScreen(onNavigate: (String)-> Unit){
    Column(){
        Text("LISTA DE CARTAS KNY")

        //Prueba navegación a detail carta
        Button(onClick = {onNavigate("kimetsuNoYaibaDetail")}){
            Text("Ver detail de KNY")
        }
    }
}