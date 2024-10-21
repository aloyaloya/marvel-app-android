package com.example.marvel_application.presentation.ui.screens.main_screen.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvel_application.presentation.ui.screens.main_screen.MarvelCharacters
import com.example.marvel_application.presentation.ui.theme.Dimens

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    onClick: (id: Int) -> Unit
) {
    val characters = MarvelCharacters.characters
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    LazyRow(
        modifier = modifier,
        state = lazyListState,
        flingBehavior = snapBehavior,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(Dimens.largePadding)
    ) {
        items(characters) { character ->
            CharacterCard(
                modifier = Modifier.fillParentMaxSize(),
                id = character.id,
                name = character.name,
                imageUrl = character.imageUrl,
                onClick = onClick,
            )
        }
    }
}