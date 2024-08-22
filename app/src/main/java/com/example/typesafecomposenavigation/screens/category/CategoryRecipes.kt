package com.example.typesafecomposenavigation.screens.category

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.typesafecomposenavigation.data.RecipeRepository
import com.example.typesafecomposenavigation.model.RecipeModel
import com.example.typesafecomposenavigation.model.RecipeType
import com.example.typesafecomposenavigation.screens.common.RecipeListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRecipesScreen(
    recipeType: RecipeType,
    onBackPressed: () -> Unit,
    onRecipeClicked: (Int) -> Unit,
) {
    var recipes by remember {
        mutableStateOf(emptyList<RecipeModel>())
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("${recipeType.name} Recipes") }, navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "go back",
                )
            }
        })
    }) {
        RecipeListScreen(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            recipes = recipes,
            onRecipeClicked,
        )
    }

    LaunchedEffect(recipeType) {
        recipes = RecipeRepository.getRecipesByType(recipeType)
    }
}
