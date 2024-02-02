package com.example.frikartas.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frikartas.domain.models.Card
import com.example.frikartas.domain.models.Collection
import com.example.frikartas.domain.repositories.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: CardRepository) : ViewModel() {
    val collections = MutableLiveData<List<Collection>>()

    init {
        loadCollections()
    }

    fun loadCollections() {
        viewModelScope.launch {
            collections.value = repository.getCardCollections()
        }
    }

    fun getCollectionByName(name: String): Collection? {
        val normalizedName = name.trim().toLowerCase()
        return collections.value?.find { it.name.trim().toLowerCase() == normalizedName }
    }

    fun getCardFromCollection(collectionName: String, cardName: String): Card? {
        val collection = collections.value?.find { it.name.trim().toLowerCase() == collectionName.trim().toLowerCase() }
        return collection?.cards?.find { it.name.trim().toLowerCase() == cardName.trim().toLowerCase() }
    }

    private val _navigationEvents = MutableSharedFlow<String>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    fun onCollectionSelected(collectionName: String) {
        viewModelScope.launch {
            _navigationEvents.emit("onePieceCollectionDetail/$collectionName")
        }
    }
}