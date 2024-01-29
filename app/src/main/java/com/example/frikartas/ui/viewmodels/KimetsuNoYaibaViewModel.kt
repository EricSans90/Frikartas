package com.example.frikartas.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frikartas.domain.models.KimetsuNoYaibaCard
import com.example.frikartas.domain.repositories.KimetsuNoYaibaRepository

class KimetsuNoYaibaViewModel(private val KimetsuNoYaibaRepository: KimetsuNoYaibaRepository) : ViewModel() {

    val cards = MutableLiveData<List<KimetsuNoYaibaCard>>()

    init {
        loadCards()
    }

    private fun loadCards() {
        // Simula la carga de datos
        cards.value = KimetsuNoYaibaRepository.getKimetsuNoYaibaCards()
    }
}