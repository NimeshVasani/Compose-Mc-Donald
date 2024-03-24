package com.example.composemcdonald.ui.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemcdonald.ui.components.Icon
import com.example.composemcdonald.ui.theme.McComposeTheme
import androidx.compose.material.icons.Icons as Icons1

@Composable
fun CartButton(
    quantity: Int,
    price: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    if (quantity == 1)
                        "$quantity item"
                    else
                        "$quantity items",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "$" + "%.2f".format(price),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "VIEW CART",
                style = MaterialTheme.typography.bodySmall
            )
            Icon(Icons1.Rounded.CheckCircle)
        }
    }
}

@Preview("MenuCartButton")
@Composable
private fun MenuCartButtonPreview() {
    McComposeTheme {
        CartButton(
            quantity = 3,
            price = 0.0,
            onClick = {}
        )
    }
}

@Preview("MenuCartButton • Dark")
@Composable
private fun MenuCartButtonDarkPreview() {
    McComposeTheme(lightTheme = false) {
        CartButton(
            quantity = 3,
            price = 0.0,
            onClick = {}
        )
    }
}