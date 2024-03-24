package com.example.composemcdonald.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Icon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.Icon(
        imageVector = imageVector,
        modifier = modifier,
        contentDescription = null
    )
}
