package com.example.typesafecomposenavigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.typesafecomposenavigation.data.RecipeRepository
import com.example.typesafecomposenavigation.model.RecipeType
import com.example.typesafecomposenavigation.screens.category.CategoryRecipesScreen
import com.example.typesafecomposenavigation.screens.category.CategoryScreen
import com.example.typesafecomposenavigation.screens.favorites.FavoriteRecipesScreen
import com.example.typesafecomposenavigation.screens.recipedetail.RecipeDetailPage
import com.example.typesafecomposenavigation.screens.recipelist.AllRecipesScreen
import kotlin.reflect.typeOf


@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: RecipeDestinations = RecipeDestinations.Recipes,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        composable<RecipeDestinations.Recipes> {


            AllRecipesScreen(RecipeRepository.getAllRecipes()) { recipeId ->
                navController.navigate(RecipeDestinations.RecipeDetails(recipeId)) {
                    launchSingleTop = true
                }
            }

        }


        composable<RecipeDestinations.RecipeDetails> { backStackEntry ->
            val recipeDetails: RecipeDestinations.RecipeDetails = backStackEntry.toRoute()

            RecipeDetailPage(recipeDetails.recipeId) {
                navController.navigateUp()
            }

        }

        composable<RecipeDestinations.Favorites> {


            FavoriteRecipesScreen(onRecipeClicked = { recipeId ->
                navController.navigate(RecipeDestinations.RecipeDetails(recipeId)) {
                    launchSingleTop = true
                }
            }) {
                navController.navigate(RecipeDestinations.Recipes)
            }

        }

        composable<RecipeDestinations.Category> {

            CategoryScreen {
                navController.navigate(RecipeDestinations.CategoryRecipes(it))
            }

        }
        composable<RecipeDestinations.CategoryRecipes>(
            // typeMap = mapOf(typeOf<RecipeType>() to NavType.EnumType(RecipeType::class.java))
            // we are using the custom navtype for demonstration purposes,but it is not required we can use NavType.EnumType
            typeMap = mapOf(typeOf<RecipeType>() to CategoryNavigationType)
        ) { backStackEntry ->

            val category: RecipeDestinations.CategoryRecipes =
                backStackEntry.toRoute<RecipeDestinations.CategoryRecipes>()

            CategoryRecipesScreen(
                category.type,
                onBackPressed = { navController.navigateUp() }) { recipeId ->
                navController.navigate(RecipeDestinations.RecipeDetails(recipeId))
            }


        }
    }


}