package com.example.marvel_application.presentation.ui.screens.character_card_screen

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

sealed interface CharacterScreenState {
    data object Loading : CharacterScreenState
    data class Success(val character: CharacterUI) : CharacterScreenState
    data class Error(val message: String) : CharacterScreenState
}

@HiltViewModel
class CharacterCardViewModel @Inject constructor(
    private val repository: MarvelRepository,
    private val characterMapper: CharacterMapper
) : ViewModel() {

    private val _screenState = MutableStateFlow<CharacterScreenState>(CharacterScreenState.Loading)
    val screenState: StateFlow<CharacterScreenState> = _screenState

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.value = CharacterScreenState.Loading
            try {
                val characterDetail = repository.getCharacterById(id)
                if (characterDetail != null) {
                    _screenState.value = CharacterScreenState.Success(
                        characterDetail.let { characterMapper.mapDomainToUI(it) }
                    )
                } else {
                    _screenState.value = CharacterScreenState.Error(
                        R.string.character_screen_error_message.toString()
                    )
                }
            } catch (e: Exception) {
                _screenState.value = CharacterScreenState.Error(
                    "Failed to fetch character: ${e.message}"
                )
            }
        }
    }
}