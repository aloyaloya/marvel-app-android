package com.example.marvel_application.presentation.ui.screens.character_card_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.marvel_application.R
import com.example.marvel_application.presentation.ui.screens.main_screen.MarvelCharacter
import com.example.marvel_application.presentation.ui.screens.main_screen.MarvelCharacters
import com.example.marvel_application.presentation.ui.theme.Dimens

@Composable
fun CharacterCardScreen(
    modifier: Modifier = Modifier,
    characterId: Int,
    onClick: () -> Unit
) {
    val character = getCharacterById(characterId)
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = character.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.extraLargePadding)
        ) {
            IconButton(onClick = onClick) {
                Icon(
                    modifier = Modifier.size(width = 32.dp, height = 28.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(
                        id = R.string.content_description_to_main_button
                    ),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(Dimens.mediumSpacerHeight))
            Text(
                text = character.quote,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun getCharacterById(characterId: Int): MarvelCharacter {
    val characters = MarvelCharacters.characters
    return characters.first { it.id == characterId }
}