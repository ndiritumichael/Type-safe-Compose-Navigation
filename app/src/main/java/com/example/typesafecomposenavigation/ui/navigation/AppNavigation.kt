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
    startDestination: RecipeScreens = RecipeScreens.Recipes,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        composable<RecipeScreens.Recipes> {


            AllRecipesScreen(RecipeRepository.getAllRecipes()) { recipeId ->
                navController.navigate(RecipeScreens.RecipeDetails(recipeId)) {
                    launchSingleTop = true
                }
            }

        }


        composable<RecipeScreens.RecipeDetails> { backStackEntry ->
            val recipeDetails: RecipeScreens.RecipeDetails = backStackEntry.toRoute()

            RecipeDetailPage(recipeDetails.recipeId) {
                navController.navigateUp()
            }

        }

        composable<RecipeScreens.Favorites> {


            FavoriteRecipesScreen(onRecipeClicked = { recipeId ->
                navController.navigate(RecipeScreens.RecipeDetails(recipeId)) {
                    launchSingleTop = true
                }
            }) {
                navController.navigate(RecipeScreens.Recipes)
            }

        }

        composable<RecipeScreens.Category> {

            CategoryScreen {
                navController.navigate(RecipeScreens.CategoryRecipes(it))
            }

        }
        composable<RecipeScreens.CategoryRecipes>(
            typeMap = mapOf(typeOf<RecipeType>() to CategoryNavigationType)
        ) { backStackEntry ->

            val category: RecipeScreens.CategoryRecipes =
                backStackEntry.toRoute<RecipeScreens.CategoryRecipes>()

            CategoryRecipesScreen(
                category.type,
                onBackPressed = { navController.navigateUp() }) { recipeId ->
                navController.navigate(RecipeScreens.RecipeDetails(recipeId))
            }


        }
    }


}