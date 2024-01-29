package com.example.frikartas.data.sources.local

import android.content.Context
import com.example.frikartas.data.models.KimetsuNoYaibaCardDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class KimetsuNoYaibaLocalDataSource(private val context: Context) {
    fun getKimetsuNoYaibaCards(): List<KimetsuNoYaibaCardDTO>{
        val jsonFileString = getJsonDataFromAsset(context, "KimetsuNoYaibaJSON.json")
        val listType = object : TypeToken<List<KimetsuNoYaibaCardDTO>>() {}.type
        return Gson().fromJson(jsonFileString, listType)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
