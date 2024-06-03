package com.example.typesafecomposenavigation.screens.recipelist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.typesafecomposenavigation.model.RecipeModel
import com.example.typesafecomposenavigation.screens.common.RecipeListScreen


/*
Recipe List views
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AllRecipesScreen(recipes : List<RecipeModel>,onRecipeClicked : (recipeId : Int) -> Unit) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "My Recipes") },
                scrollBehavior = scrollBehavior,

            )
        }
    ){ padding ->

        RecipeListScreen(modifier = Modifier.padding(top = padding.calculateTopPadding()),recipes, onRecipeClicked)




    }





}


