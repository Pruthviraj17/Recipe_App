package com.example.myreciepeapp

import android.graphics.Paint.Align
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import kotlin.math.ceil

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState: MainViewModel.RecipeState,
    navigateToDetailsScreen: (Category) -> Unit
) {

    val receipeViewModel: MainViewModel = viewModel()


    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "Error Occured")
            }

            else -> {
                // display categories
                CategoryScreen(
                    categories = viewState.list,
                    navigateToDetailsScreen
                )
            }
        }
    }
}

// how the whole category screen look like
@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetailsScreen: (Category) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(category = category, navigateToDetailsScreen)
        }
    }
}

// how each category item should look like
@Composable
fun CategoryItem(category: Category, navigateToDetailsScreen: (Category) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { navigateToDetailsScreen(category) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}