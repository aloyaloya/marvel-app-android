package com.example.marvel_application.presentation.ui.screens.main_screen

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
class MainScreenViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {
    private val _characters = MutableStateFlow<List<MarvelCharacter>>(emptyList())
    val characters: StateFlow<List<MarvelCharacter>> = _characters

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val charactersList = repository.getCharacters()
            charactersList?.let {
                _characters.value = it
            }
        }
    }

    fun getCharacterById(id: Int): MarvelCharacter? {
        return _characters.value.firstOrNull { it.id == id }
    }
}