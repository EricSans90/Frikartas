package com.example.frikartas.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.repositories.OnePieceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnePieceViewModel @Inject constructor(private val repository: OnePieceRepository) : ViewModel() {
    val cards = MutableLiveData<List<OnePieceCard>>()

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch {
            cards.value = repository.getOnePieceCards()
        }
    }

    fun getCardByName(name: String): OnePieceCard? {
        return cards.value?.find { it.name == name }
    }
}