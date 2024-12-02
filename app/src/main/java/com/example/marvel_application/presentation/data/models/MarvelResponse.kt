package com.example.marvel_application.presentation.data.models

data class MarvelResponse(
    val data: MarvelData
)

data class MarvelData(
    val results: List<MarvelCharacterDTO>
)

data class MarvelCharacterDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
)

data class Thumbnail(
    val path: String,
    val extension: String
)