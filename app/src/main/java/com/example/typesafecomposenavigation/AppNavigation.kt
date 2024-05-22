package com.example.typesafecomposenavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute


@Composable
fun AppNavigation(navController: NavHostController,startDestination: RecipeScreens,modifier: Modifier){

    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){


        composable<RecipeScreens.Recipes> {

        }


        composable<RecipeScreens.RecipeDetails> {backStackEntry ->
            val recipeDetails : RecipeScreens.RecipeDetails = backStackEntry.toRoute()

        }

        composable<RecipeScreens.Favorites> {

        }

        composable<RecipeScreens.Profile> {

        }

    }



}