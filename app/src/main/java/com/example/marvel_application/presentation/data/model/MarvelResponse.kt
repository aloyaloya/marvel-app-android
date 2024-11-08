package com.example.marvel_application.presentation.data.model

data class MarvelResponse(
    val data: MarvelData
)

data class MarvelData(
    val results: List<MarvelCharacter>
)

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
)

data class Thumbnail(
    val path: String,
    val extension: String
)