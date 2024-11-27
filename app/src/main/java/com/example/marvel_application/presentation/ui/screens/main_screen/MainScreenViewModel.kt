package com.example.marvel_application.presentation.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_application.R
import com.example.marvel_application.presentation.data.models.CharacterUI
import com.example.marvel_application.presentation.data.repository.CharacterMapper
import com.example.marvel_application.presentation.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MainScreenState {
    data object Loading : MainScreenState
    data class Success(val characters: List<CharacterUI>) : MainScreenState
    data class Error(val message: String) : MainScreenState
}

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MarvelRepository,
    private val characterMapper: CharacterMapper
) : ViewModel() {

    private val _screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val screenState: StateFlow<MainScreenState> = _screenState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.value = MainScreenState.Loading
            try {
                val charactersList = repository.getCharacters()
                if (!charactersList.isNullOrEmpty()) {
                    _screenState.value = MainScreenState.Success(
                        charactersList.map { characterMapper.mapDomainToUI(it) }
                    )
                } else {
                    _screenState.value = MainScreenState.Error(
                        R.string.main_screen_error_message.toString()
                    )
                }
            } catch (e: Exception) {
                _screenState.value = MainScreenState.Error(
                    "Failed to fetch characters: ${e.message}"
                )
            }
        }
    }

    fun getCharacterById(id: Int): CharacterUI? {
        val currentState = _screenState.value
        return if (currentState is MainScreenState.Success) {
            currentState.characters.firstOrNull { it.id == id }
        } else {
            null
        }
    }
}