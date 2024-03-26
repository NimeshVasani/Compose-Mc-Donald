package  com.example.composemcdonald.ui.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.composemcdonald.R
import com.example.composemcdonald.model.Menu
import com.example.composemcdonald.model.MenuItem
import com.example.composemcdonald.ui.components.NetworkImage
import com.example.composemcdonald.ui.menu.MenuViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: MenuViewModel,
    onMenuItemClick: () -> Unit,
) {
    val data by viewModel.data.observeAsState(Menu(emptyList(), emptyList()))
    val lazyListState = rememberLazyListState()

    Scaffold(

    ) { padding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)

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
                CartItemCard(
                    menuItem = cartItem,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun CartItemCard(
    menuItem: MenuItem,
    viewModel: MenuViewModel
) {
    val randomColor = remember {
        getRandomLightColor()
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = randomColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { }, // Handle click event

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            // Image
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NetworkImage(
                    imageUrl = menuItem.image,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(0.4f)
                        .aspectRatio(2f),

                    previewPlaceholder = R.drawable.menu_item_double_quarter_pounder_with_cheese_meal
                )
                // Add and Remove buttons
                Row(verticalAlignment = Alignment.Bottom) {
                    IconButton(onClick = {
                        viewModel.incrementMenuItemQuantity(menuItem)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }

                    IconButton(onClick = {
                        viewModel.decrementMenuItemQuantity(menuItem)

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
                    style = MaterialTheme.typography.bodyLarge,
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
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row (horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(),){
                    Text(
                        text = "Quantity: ${menuItem.quantity}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    IconButton(onClick = {
                        viewModel.removeAllMenuItemQuantity(menuItem)

                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete All"
                        )
                    }
                }
            }
        }
    }
}

fun getRandomLightColor(): Color {
    val random = Random.Default
    val red = random.nextFloat() * 0.2f + 0.8f
    val green = random.nextFloat() * 0.2f + 0.8f
    val blue = random.nextFloat() * 0.2f + 0.8f
    return Color(red, green, blue)
}
