package com.example.marvel_application.presentation.ui.screens.character_card_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_application.presentation.data.model.MarvelCharacter
import com.example.marvel_application.presentation.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterCardViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _character = MutableStateFlow<MarvelCharacter?>(null)
    val character: StateFlow<MarvelCharacter?> = _character

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val characterDetail = repository.getCharacterById(id)
            if (characterDetail != null) {
                _character.value = characterDetail.data.results.firstOrNull()
            }
        }
    }
}