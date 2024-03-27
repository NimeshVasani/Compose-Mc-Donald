package  com.example.composemcdonald.ui.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemcdonald.R
import com.example.composemcdonald.model.Menu
import com.example.composemcdonald.model.MenuItem
import com.example.composemcdonald.ui.components.NetworkImage
import com.example.composemcdonald.ui.menu.CartButton
import com.example.composemcdonald.ui.menu.MenuViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: MenuViewModel,
    onMenuItemClick: (Long) -> Unit,
    onBackClick: () -> Unit
) {
    val data by viewModel.data.observeAsState(Menu(emptyList(), emptyList()))
    val lazyListState = rememberLazyListState()
    val menuItems = data.menuItems.filter { it.quantity > 0 }

    Scaffold(

    ) { padding ->

        if (menuItems.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "cart",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your Cart is Empty..!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(0.3f)
                        .height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = "Add", fontSize = 20.sp, textAlign = TextAlign.Center)
                }
            }
        } else {
            Box(modifier = Modifier.padding(top = 30.dp)) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    IconButton(onClick = onBackClick) {
                        com.example.composemcdonald.ui.components.Icon(
                            Icons.Rounded.KeyboardArrowLeft,
                            modifier = Modifier.height(50.dp).width(50.dp)
                        )
                    }
                    Text(
                        text = "Your Cart",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Icon(Icons.Default.ShoppingCart, contentDescription = "cart")
                }

                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 60.dp)

                ) {
                    item { Spacer(modifier = Modifier.height(16.dp)) }

                    itemsIndexed(menuItems) { index, cartItem ->
                        CartItemCard(
                            menuItem = cartItem,
                            viewModel = viewModel,
                            onMenuItemClick = onMenuItemClick
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item { Spacer(modifier = Modifier.height(80.dp)) }

                }
                AnimatedVisibility(
                    visible = menuItems.isNotEmpty(),
                    enter = slideInVertically(initialOffsetY = { it * 2 }),
                    exit = slideOutVertically(targetOffsetY = { it * 2 }),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    CartButton(
                        quantity = data.menuItems.sumOf { it.quantity },
                        price = menuItems.sumOf { it.price * it.quantity },
                        onClick = { }, // Send the selected menu item IDs here
                        contentName = "CHECK OUT",
                        contentIcon = Icons.Rounded.CheckCircle,
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                }
            }
        }
    }

}

@Composable
fun CartItemCard(
    menuItem: MenuItem,
    viewModel: MenuViewModel,
    onMenuItemClick: (Long) -> Unit
) {
    val randomColor = remember {
        getRandomLightColor()
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = randomColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMenuItemClick(menuItem.id) }, // Handle click event

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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        viewModel.incrementMenuItemQuantity(menuItem)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                    Text(
                        text = "${menuItem.quantity}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
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
                    text = "Total Price: $${
                        String.format(
                            "%.2f",
                            menuItem.price * menuItem.quantity
                        )
                    }   ($${menuItem.price} ea.)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {

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
