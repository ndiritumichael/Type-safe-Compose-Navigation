package com.example.typesafecomposenavigation.screens.recipedetail

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Stairs
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.typesafecomposenavigation.R
import com.example.typesafecomposenavigation.model.RecipeModel
import com.example.typesafecomposenavigation.ui.theme.LightBlue
import com.example.typesafecomposenavigation.ui.theme.LightGreen
import com.example.typesafecomposenavigation.ui.theme.LightPink
import com.example.typesafecomposenavigation.ui.theme.LightPurple
import com.example.typesafecomposenavigation.ui.theme.LightYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailPage(
    recipeId: Int,
    viewModel: RecipeDetailViewModel = viewModel(),
    onBackPressed: () -> Unit
) {

    val recipeState by viewModel.recipeDetails.collectAsState()


    val recipe = (recipeState as? RecipeDetailState.Success)?.recipe

    var isFavourite by remember(recipe) {
        mutableStateOf(recipe?.isFavorite ?: false)
    }



    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Details") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {


                recipe?.let {

                    IconButton(onClick = {


                        isFavourite = !isFavourite


                        viewModel.toggleFavorite(it.id, it.isFavorite)
                    }


                    ) {
                        Icon(
                            painter = if (isFavourite) painterResource(R.drawable.baseline_favorite_24) else painterResource(
                                R.drawable.baseline_favorite_border_24
                            ),
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }


                }

            },

            )


    }) {
        Box(modifier = Modifier.padding(it), contentAlignment = Alignment.Center) {
            when (val state = recipeState) {
                is RecipeDetailState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                        CircularProgressIndicator(modifier = Modifier.size(75.dp))
                    }
                }

                is RecipeDetailState.Success -> {

                    RecipeDetailsScreen(recipe = state.recipe)
                }

                is RecipeDetailState.Error -> {

                    Text(text = "Error: ${state.message}")
                }

                is RecipeDetailState.Idle -> {
                }
            }

        }

        LaunchedEffect(recipeId) {

            viewModel.getRecipeById(recipeId)
        }

    }
}


@Composable
fun RecipeDetailsScreen(recipe: RecipeModel) {


    Column(
        modifier = Modifier

            .verticalScroll(
                rememberScrollState()
            )
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {


        // Recipe Name Card
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),

            ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.basicMarquee()
                )
            }
        }

        // Meal Type Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = LightBlue.copy(alpha = 0.3f)
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Fastfood,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Meal Type: ${recipe.mealType.name}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Difficulty Level Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = LightYellow.copy(alpha = 0.3f)
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Stairs,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Difficulty Level: ${recipe.difficultyLevel.name}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Ingredients Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = LightPurple.copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Inventory,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
                recipe.ingredients.forEach { ingredient ->
                    Text(
                        text = "- ${ingredient.amount} ${ingredient.name}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Steps Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = LightGreen.copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Steps",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
                recipe.steps.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Servings Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = LightPink.copy(alpha = 0.3f)
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Servings: ${recipe.servings}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}