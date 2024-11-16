package com.example.marvel_application.presentation.data.repository

import com.example.marvel_application.presentation.data.model.MarvelCharacter

fun List<MarvelCharacter>.mapValidCharacters(): List<MarvelCharacter> {
    return this.filter { character ->
        character.description.isNotEmpty() &&
                character.thumbnail?.path != null &&
                character.thumbnail.extension == "jpg" &&
                !character.thumbnail.path.contains("image_not_available", ignoreCase = true)
    }
}