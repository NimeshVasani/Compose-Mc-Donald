package com.example.composemcdonald.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemcdonald.data.HomeRepository
import com.example.composemcdonald.ui.components.SearchBar
import com.example.composemcdonald.ui.components.SpotlightCard
import com.example.composemcdonald.ui.theme.McComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCategoryClick: () -> Unit,
    onMenuItemClick: () -> Unit,
) {

    val data = HomeRepository.getHomeData()

    Scaffold(
       Modifier.background(Color.White)
    ) {padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Text(
                    text = "Hi ${data.user.name}!",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                SearchBar(
                    text = "Find what you want...",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                LazyRow {
                    item { Spacer(modifier = Modifier.width(16.dp)) }
                    items(data.categories) { category ->
                        SpotlightCard(
                            title = category.name,
                            imageUrl = category.image,
                            onClick = onCategoryClick
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Text(
                    text = "Popular",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            items(data.popularMenuItems) { menuItem ->
                MenuItemCard(
                    menuItem = menuItem,
                    onClick = onMenuItemClick
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            items(data.recommendedMenuItems) { menuItem ->
                MenuItemCard(
                    menuItem = menuItem,
                    onClick = onMenuItemClick
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Preview("HomeScreen")
@Composable
private fun HomeScreenPreview() {
    McComposeTheme {
        HomeScreen(
            onCategoryClick = {},
            onMenuItemClick = {},
        )
    }
}

@Preview("HomeScreen • Dark")
@Composable
private fun HomeScreenDarkPreview() {
    McComposeTheme() {
        HomeScreen(
            onCategoryClick = {},
            onMenuItemClick = {},
        )
    }
}