package com.example.composemcdonald.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemcdonald.R
import com.example.composemcdonald.ui.components.Icon
import com.example.composemcdonald.data.MenuRepository
import com.example.composemcdonald.ui.components.NetworkImage
import com.example.composemcdonald.ui.theme.McComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    menuItemId: Long,
    onBackClick: () -> Unit
) {

    val menuItem = MenuRepository.getMenuData().menuItems.find { it.id == menuItemId }!!

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = menuItem.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 5.dp)
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.40f)
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    NetworkImage(
                        imageUrl = menuItem.image,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        previewPlaceholder = R.drawable.menu_item_double_quarter_pounder_with_cheese_meal
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Text(
                    text = "$" + "%.2f".format(menuItem.price),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

        }
    }
}

@Preview("DetailsScreen")
@Composable
private fun DetailsScreenPreview() {
    McComposeTheme {
        DetailsScreen(
            menuItemId = 4004,
            onBackClick = {}
        )
    }
}

@Preview("DetailsScreen • Dark")
@Composable
private fun DetailsScreenDarkPreview() {
    McComposeTheme() {
        DetailsScreen(
            menuItemId = 4004,
            onBackClick = {}
        )
    }
}
