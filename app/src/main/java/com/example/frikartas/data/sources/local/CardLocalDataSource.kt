package com.example.frikartas.data.sources.local

import android.content.Context
import com.example.frikartas.data.models.CollectionDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CardLocalDataSource(private val context: Context) {
    fun getCollections(): List<CollectionDTO> {
        val jsonFileString = getJsonDataFromAsset(context, "CollectionsJSON.json")
        val listType = object : TypeToken<List<CollectionDTO>>() {}.type
        return Gson().fromJson(jsonFileString, listType)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}