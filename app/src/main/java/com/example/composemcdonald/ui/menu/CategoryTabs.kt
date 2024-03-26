package com.example.composemcdonald.ui.menu

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemcdonald.R
import com.example.composemcdonald.data.CategoriesRepository
import com.example.composemcdonald.model.Category
import com.example.composemcdonald.ui.components.NetworkImage
import com.example.composemcdonald.ui.theme.McComposeTheme

@Composable
fun CategoryTabs(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = categories.indexOf(selectedCategory),
       // backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = Color.White,
        edgePadding = 8.dp,
        indicator = {},
        divider = {}
    ) {
        categories.forEach { category ->
            CategoryTab(
                category = category,
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            )
        }
    }
}


private enum class CategoryTabState { Selected, NotSelected }

@Composable
private fun CategoryTab(
    category: Category,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val transition = updateTransition(if (selected) CategoryTabState.Selected else CategoryTabState.NotSelected,
        label = ""
    )

    val backgroundColor by transition.animateColor(label = "") { state ->
        when (state) {
            CategoryTabState.Selected ->  Color(255, 205, 210)
            CategoryTabState.NotSelected -> MaterialTheme.colorScheme.background
        }
    }
    val contentColor by transition.animateColor(label = "") { state ->
        when (state) {
            CategoryTabState.Selected -> Color.Black
            CategoryTabState.NotSelected -> Color.Gray
        }
    }

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                imageUrl = category.image,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
                previewPlaceholder = R.drawable.category_burgers
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview("CategoryTabs")
@Composable
private fun CategoryTabsPreview() {
    McComposeTheme {
        CategoryTabs(
            categories = CategoriesRepository.getCategoriesData(),
            selectedCategory = CategoriesRepository.getCategoriesData().first(),
            onCategorySelected = {}
        )
    }
}

@Preview("CategoryTabs • Dark")
@Composable
private fun CategoryTabsDarkPreview() {
    McComposeTheme() {
        CategoryTabs(
            categories = CategoriesRepository.getCategoriesData(),
            selectedCategory = CategoriesRepository.getCategoriesData().first(),
            onCategorySelected = {}
        )
    }
}

@Preview("CategoryTab • Selected")
@Composable
private fun CategoryTabSelectedPreview() {
    McComposeTheme {
        CategoryTab(
            category = Category(
                id = 0,
                name = "Burgers",
                image = ""
            ),
            selected = true,
            onClick = {}
        )
    }
}

@Preview("CategoryTab • NotSelected")
@Composable
private fun CategoryTabNotSelectedPreview() {
    McComposeTheme {
        CategoryTab(
            category = Category(
                id = 0,
                name = "Burgers",
                image = ""
            ),
            selected = false,
            onClick = {}
        )
    }
}

@Preview("CategoryTab • Selected • Dark")
@Composable
private fun CategoryTabSelectedDarkPreview() {
    McComposeTheme() {
        CategoryTab(
            category = Category(
                id = 0,
                name = "Burgers",
                image = ""
            ),
            selected = true,
            onClick = {}
        )
    }
}

@Preview("CategoryTab • NotSelected • Dark")
@Composable
private fun CategoryTabNotSelectedDarkPreview() {
    McComposeTheme() {
        CategoryTab(
            category = Category(
                id = 0,
                name = "Burgers",
                image = ""
            ),
            selected = false,
            onClick = {}
        )
    }
}
