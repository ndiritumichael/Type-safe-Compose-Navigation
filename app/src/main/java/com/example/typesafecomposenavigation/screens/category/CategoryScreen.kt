package com.example.typesafecomposenavigation.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.typesafecomposenavigation.R
import com.example.typesafecomposenavigation.model.RecipeType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(onCategorySelected: (RecipeType) -> Unit){

    val categories  = remember {
        listOf(
            RecipeType.Snack to R.drawable.snack,
            RecipeType.Breakfast to R.drawable.breakfast,
            RecipeType.Lunch to R.drawable.lunch,
            RecipeType.Supper to R.drawable.dinner
        )
    }

    Scaffold(topBar = { TopAppBar(title = {Text("Explore Categories ")}) }) {


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(it)
        ) {
            items(categories) { category ->
                CategoryCard(category, onCategorySelected = {
                    onCategorySelected(category.first)
                })
            }

        }

    }

}

@Composable
fun CategoryCard(category: Pair<RecipeType, Int>,onCategorySelected: () -> Unit) {
    val (categoryType, image) = category
    Card(
        onClick = onCategorySelected,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(image),
                contentDescription = categoryType.name,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = categoryType.name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}