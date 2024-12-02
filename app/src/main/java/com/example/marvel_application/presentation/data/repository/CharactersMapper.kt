package com.example.marvel_application.presentation.data.repository

import com.example.marvel_application.presentation.data.database.entity.CharacterEntity
import com.example.marvel_application.presentation.data.models.CharacterDomain
import com.example.marvel_application.presentation.data.models.CharacterUI
import com.example.marvel_application.presentation.data.models.MarvelCharacterDTO
import javax.inject.Inject

class CharacterMapper @Inject constructor() {

    fun mapDtoToDomain(dto: MarvelCharacterDTO): CharacterDomain {
        val url: String? = dto.thumbnail?.let {
            (it.path + "." + it.extension).toHttpsPrefix()
        }

        return CharacterDomain(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnailUrl = url
        )
    }

    fun mapEntityToDomain(entity: CharacterEntity): CharacterDomain {
        val url = (entity.path + "." + entity.extension).toHttpsPrefix()

        return CharacterDomain(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            thumbnailUrl = url
        )
    }

    fun mapDtoToEntity(dto: MarvelCharacterDTO): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            path = dto.thumbnail!!.path,
            extension = dto.thumbnail.extension
        )
    }

    fun mapDomainToUI(domain: CharacterDomain): CharacterUI {
        return CharacterUI(
            id = domain.id,
            name = domain.name,
            description = domain.description,
            thumbnailUrl = domain.thumbnailUrl
        )
    }

    fun mapValidCharacters(characters: List<MarvelCharacterDTO>): List<MarvelCharacterDTO> {
        return characters.filter { character ->
            character.description.isNotEmpty() &&
                    character.thumbnail?.path != null &&
                    character.thumbnail.extension == "jpg" &&
                    !character.thumbnail.path.contains("image_not_available", ignoreCase = true)
        }
    }

    private fun String.toHttpsPrefix(): String =
        if (startsWith("http://")) replace("http://", "https://") else this
}