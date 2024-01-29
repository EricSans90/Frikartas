package com.example.frikartas.data.sources.local

import android.content.Context
import com.example.frikartas.data.models.OnePieceCardDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OnePieceLocalDataSource(private val context: Context) {
    fun getOnePieceCards(): List<OnePieceCardDTO>{
        val jsonFileString = getJsonDataFromAsset(context, "OnePieceJSON.json")
        val listType = object : TypeToken<List<OnePieceCardDTO>>() {}.type
        return Gson().fromJson(jsonFileString, listType)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}