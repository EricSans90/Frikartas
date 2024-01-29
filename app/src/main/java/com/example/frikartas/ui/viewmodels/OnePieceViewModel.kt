package com.example.frikartas.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.repositories.OnePieceRepository

class OnePieceViewModel(private val onePieceRepository: OnePieceRepository) : ViewModel() {

    val cards = MutableLiveData<List<OnePieceCard>>()

    init {
        loadCards()
    }

    private fun loadCards() {
        // Simula la carga de datos
        cards.value = onePieceRepository.getOnePieceCards()
    }
}