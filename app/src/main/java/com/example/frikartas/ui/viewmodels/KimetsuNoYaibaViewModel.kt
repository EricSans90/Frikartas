package com.example.frikartas.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frikartas.domain.models.KimetsuNoYaibaCard
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.repositories.KimetsuNoYaibaRepository
import com.example.frikartas.domain.repositories.OnePieceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KimetsuNoYaibaViewModel @Inject constructor(private val repository: KimetsuNoYaibaRepository) : ViewModel() {
    val cards = MutableLiveData<List<KimetsuNoYaibaCard>>()

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch {
            cards.value = repository.getKimetsuNoYaibaCards()
        }
    }

    fun getCardByName(name: String): KimetsuNoYaibaCard? {
        return cards.value?.find { it.name == name }
    }
}