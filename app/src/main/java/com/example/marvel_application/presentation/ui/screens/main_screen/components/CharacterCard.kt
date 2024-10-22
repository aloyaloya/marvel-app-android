package com.example.marvel_application.presentation.ui.screens.main_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.marvel_application.R

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    id: Int,
    name: String,
    imageUrl: String,
    onClick: (id: Int) -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable {
                onClick(id)
            },
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.large_padding),
                    vertical = dimensionResource(id = R.dimen.extra_large_padding)
                ),
            text = name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}