package com.example.marvel_application.presentation.data.repository

import com.example.marvel_application.presentation.data.model.MarvelCharacter
import com.example.marvel_application.presentation.data.model.MarvelResponse
import com.example.marvel_application.presentation.network.MarvelApiService
import javax.inject.Inject

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
                            !character.thumbnail.path.contains("image_not_available", ignoreCase = true)
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getCharacterById(id: Int): MarvelResponse? {
        return try {
            val response = apiService.getCharacterById(id)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}