package com.example.frikartas.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.models.OnePieceCollection
import com.example.frikartas.domain.repositories.OnePieceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnePieceViewModel @Inject constructor(private val repository: OnePieceRepository) : ViewModel() {
    val collections = MutableLiveData<List<OnePieceCollection>>()

    init {
        loadCollections()
    }

    fun loadCollections() {
        viewModelScope.launch {
            collections.value = repository.getOnePieceCollections()
        }
    }

    fun getCollectionByName(name: String): OnePieceCollection? {
        val normalizedName = name.trim().toLowerCase()
        return collections.value?.find { it.name.trim().toLowerCase() == normalizedName }
    }

    fun getCardFromCollection(collectionName: String, cardName: String): OnePieceCard? {
        val collection = collections.value?.find { it.name.trim().toLowerCase() == collectionName.trim().toLowerCase() }
        return collection?.cards?.find { it.name.trim().toLowerCase() == cardName.trim().toLowerCase() }
    }
}