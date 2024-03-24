package com.example.composemcdonald.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    fadeIn: Boolean = true,
    @DrawableRes previewPlaceholder: Int = 0
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
        ),
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
    )
}
