package com.example.composemcdonald.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemcdonald.R
import com.example.composemcdonald.ui.components.Icon
import com.example.composemcdonald.data.MenuRepository
import com.example.composemcdonald.ui.components.NetworkImage
import com.example.composemcdonald.ui.menu.MenuViewModel
import com.example.composemcdonald.ui.theme.McComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    menuItemId: Long,
    viewModel: MenuViewModel,
    onBackClick: () -> Unit
) {

    val menuItem = MenuRepository.getMenuData().menuItems.find { it.id == menuItemId }!!

    Scaffold { padding ->
        Box {
            Column {
                Spacer(modifier = Modifier.height(30.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                    Text(
                        text = menuItem.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(all = 5.dp)
                ) {

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 10.dp)
                                .aspectRatio(1.10f)
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
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        Text(
                            color = Color(0xFF006400),
                            text = "$" + "%.2f".format(menuItem.price),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }

                    item {
                        Text(
                            text = menuItem.description,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                    item { Spacer(modifier = Modifier.height(16.dp)) }

                    item {
                        Text(
                            text = menuItem.contains,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

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
            onBackClick = {},
            viewModel = MenuViewModel()
        )
    }
}

@Preview("DetailsScreen • Dark")
@Composable
private fun DetailsScreenDarkPreview() {
    McComposeTheme() {
        DetailsScreen(
            menuItemId = 4004,
            onBackClick = {},
            viewModel = MenuViewModel()

        )
    }
}
