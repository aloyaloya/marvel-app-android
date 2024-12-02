package com.example.marvel_application.presentation.data.repository

import android.util.Log
import com.example.marvel_application.presentation.data.database.CharacterDao
import com.example.marvel_application.presentation.data.models.CharacterDomain
import com.example.marvel_application.presentation.network.MarvelApiService
import javax.inject.Inject

private const val LOG_TAG = "MarvelRepository"

class MarvelRepository @Inject constructor(
    private val apiService: MarvelApiService,
    private val characterDao: CharacterDao,
    private val characterMapper: CharacterMapper
) {
    suspend fun getCharacters(): List<CharacterDomain>? {
        return try {
            val localCharacters = characterDao.getCharacters()
            if (localCharacters.isNotEmpty()) {
                return localCharacters.map { characterMapper.mapEntityToDomain(it) }
            }

            val response = apiService.getCharacters()
            if (response.isSuccessful) {
                val characters = response.body()?.data?.results
                val validCharacters = characters?.let { characterMapper.mapValidCharacters(it) }
                validCharacters?.let {
                    characterDao.insertCharacters(it.map { character ->
                        characterMapper.mapDtoToEntity(character)
                    })
                }
                return validCharacters?.map { characterMapper.mapDtoToDomain(it) }
            } else {
                response.errorBody()?.string()?.let { Log.e(LOG_TAG, it) }
                null
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error during characters request", e)
            null
        }
    }

    suspend fun getCharacterById(id: Int): CharacterDomain? {
        return try {
            val localCharacter = characterDao.getCharacterById(id)
            if (localCharacter != null) {
                return characterMapper.mapEntityToDomain(localCharacter)
            }

            val response = apiService.getCharacterById(id)
            if (response.isSuccessful) {
                val characterDto = response.body()?.data?.results?.firstOrNull()
                characterDto?.let { dto ->
                    val entity = characterMapper.mapDtoToEntity(dto)
                    characterDao.insertCharacter(entity)
                    return characterMapper.mapEntityToDomain(entity)
                }
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