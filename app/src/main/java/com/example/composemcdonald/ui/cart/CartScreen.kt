package  com.example.composemcdonald.ui.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composemcdonald.R
import com.example.composemcdonald.model.Menu
import com.example.composemcdonald.model.MenuItem
import com.example.composemcdonald.ui.components.NetworkImage
import com.example.composemcdonald.ui.theme.McComposeTheme
import com.example.composemcdonald.ui.menu.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: MenuViewModel,
    onMenuItemClick: () -> Unit,
) {
    val data by viewModel.data.observeAsState(Menu(emptyList(), emptyList()))
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cart") }
            )
        }
    ) { padding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()

        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            val menuItems = data.menuItems.filter { it.quantity > 0 }

            item {
                Text(
                    text = "Your Cart",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            itemsIndexed(menuItems) { index, cartItem ->
                MenuItemCard(
                    menuItem = cartItem,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { }, // Handle click event

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Column(verticalArrangement = Arrangement.Bottom) {
                NetworkImage(
                    imageUrl = menuItem.image,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .fillMaxHeight(0.2f)
                        .aspectRatio(1f),

                    previewPlaceholder = R.drawable.menu_item_double_quarter_pounder_with_cheese_meal
                )
                // Add and Remove buttons
                Row(verticalAlignment = Alignment.Top) {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }

                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_remove),
                            contentDescription = "Remove"
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Column for text content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Price: $${menuItem.price}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Quantity: ${menuItem.quantity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}
