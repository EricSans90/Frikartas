package com.example.frikartas.data.sources.local

import android.content.Context
import com.example.frikartas.data.models.CollectionDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Fuente de datos local que proporciona acceso a las colecciones de cartas almacenadas localmente.
 *
 * @property context Contexto de la aplicación utilizado para acceder a los recursos locales.
 */
class CardLocalDataSource(private val context: Context) {
    /**
     * Obtiene las colecciones de cartas desde un archivo JSON almacenado localmente.
     *
     * @return Una lista de CollectionDTO leída desde el archivo JSON.
     */
    fun getCollections(): List<CollectionDTO> {
        val jsonFileString = getJsonDataFromAsset(context, "CollectionsJSON.json")
        val listType = object : TypeToken<List<CollectionDTO>>() {}.type
        return Gson().fromJson(jsonFileString, listType)
    }

    /**
     * Lee los datos JSON de un archivo de activos y los devuelve como String.
     *
     * @param context Contexto de la aplicación.
     * @param fileName Nombre del archivo JSON a leer.
     * @return El contenido del archivo JSON como String.
     */
    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}