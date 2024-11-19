package com.example.marvel_application.presentation.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_application.presentation.data.models.CharacterUI
import com.example.marvel_application.presentation.data.models.MarvelCharacterDTO
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

    private val _characters = MutableStateFlow<List<CharacterUI>>(emptyList())
    val characters: StateFlow<List<CharacterUI>> = _characters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val charactersList = repository.getCharacters()
            charactersList?.let {
                _characters.value = it
            }
            _isLoading.value = false
        }
    }

    fun getCharacterById(id: Int): CharacterUI? {
        return _characters.value.firstOrNull { it.id == id }
    }
}