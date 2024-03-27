package com.example.composemcdonald.ui.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemcdonald.ui.components.Icon
import com.example.composemcdonald.ui.theme.McComposeTheme
import androidx.compose.material.icons.Icons as Icons1

@Composable
fun CartButton(
    quantity: Int,
    price: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    contentName: String = "VIEW CART",
    contentIcon: ImageVector = Icons1.Default.ShoppingCart
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        color = containerColor,
        contentColor = contentColor,
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
                text = contentName,
                style = MaterialTheme.typography.bodySmall
            )
            Icon(contentIcon)
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
    McComposeTheme() {
        CartButton(
            quantity = 3,
            price = 0.0,
            onClick = {}
        )
    }
}
