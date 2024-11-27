package com.example.marvel_application.presentation.data.models

data class CharacterDomain(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String?
)