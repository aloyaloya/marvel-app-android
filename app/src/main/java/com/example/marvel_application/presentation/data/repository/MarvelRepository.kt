package com.example.marvel_application.presentation.data.repository

import android.util.Log
import com.example.marvel_application.presentation.data.model.MarvelCharacter
import com.example.marvel_application.presentation.data.model.MarvelResponse
import com.example.marvel_application.presentation.network.MarvelApiService
import javax.inject.Inject

private const val LOG_TAG = "MarvelRepository"

class MarvelRepository @Inject constructor(
    private val apiService: MarvelApiService
) {
    suspend fun getCharacters(): List<MarvelCharacter>? {
        return try {
            val response = apiService.getCharacters()
            if (response.isSuccessful) {
                response.body()?.data?.results?.filter { character ->
                    character.description.isNotEmpty() &&
                        character.thumbnail?.path != null &&
                        character.thumbnail.extension == "jpg" &&
                        !character.thumbnail.path.contains(
                            "image_not_available", ignoreCase = true
                        )
                }
            } else {
                response.errorBody()?.string()?.let { Log.e(LOG_TAG, it) }
                null
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error during characters request", e)
            null
        }
    }

    suspend fun getCharacterById(id: Int): MarvelResponse? {
        return try {
            val response = apiService.getCharacterById(id)
            if (response.isSuccessful) {
                response.body()
            } else {
                response.errorBody()?.string()?.let { Log.e(LOG_TAG, it) }
                null
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error during character by ID request", e)
            null
        }
    }
}