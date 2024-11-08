package com.example.marvel_application.presentation.network

import com.example.marvel_application.presentation.data.model.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("ts") ts: Long = MarvelConfig.ts,
        @Query("apikey") apikey: String = MarvelConfig.PUBLIC_KEY,
        @Query("hash") hash: String = MarvelConfig.getHash()
    ): Response<MarvelResponse>

    @GET("characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int,
        @Query("ts") ts: Long = MarvelConfig.ts,
        @Query("apikey") apikey: String = MarvelConfig.PUBLIC_KEY,
        @Query("hash") hash: String = MarvelConfig.getHash()
    ): Response<MarvelResponse>
}
