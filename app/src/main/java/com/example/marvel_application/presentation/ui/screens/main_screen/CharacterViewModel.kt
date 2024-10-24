package com.example.marvel_application.presentation.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterViewModel : ViewModel() {
    private val _characters = MutableStateFlow(MarvelCharacters.characters)
    val characters: StateFlow<List<MarvelCharacter>> = _characters

    fun getCharacterById(id: Int): MarvelCharacter? {
        return _characters.value.firstOrNull { it.id == id }
    }
}