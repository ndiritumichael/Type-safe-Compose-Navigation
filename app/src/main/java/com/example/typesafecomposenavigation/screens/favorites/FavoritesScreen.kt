package com.example.typesafecomposenavigation.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.typesafecomposenavigation.R
import com.example.typesafecomposenavigation.data.RecipeRepository
import com.example.typesafecomposenavigation.screens.common.RecipeListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FavoriteRecipesScreen(onRecipeClicked: (recipeId: Int) -> Unit, exploreRecipes: () -> Unit) {

    val recipes by RecipeRepository.favoriteRecipes.collectAsStateWithLifecycle(emptyList())

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = " Favorite Recipes") },
                scrollBehavior = scrollBehavior,

                )
        }
    ) { padding ->

        if (recipes.isNotEmpty()) {
            RecipeListScreen(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                recipes,
                onRecipeClicked
            )
        } else {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        painter = painterResource(R.drawable.empty_list),
                        contentDescription = "No favorites ",
                        modifier = Modifier.size(200.dp)
                    )
                    Text(
                        text = "You don't have any favorite recipes \n Explore recipes and add them to your favorites",
                        textAlign = TextAlign.Center
                    )
                    Button(onClick = exploreRecipes) {
                        Text(
                            text = "Explore Recipes",

                            )
                    }


                }
            }
        }


    }


}