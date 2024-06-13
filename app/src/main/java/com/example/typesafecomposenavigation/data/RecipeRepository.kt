package com.example.typesafecomposenavigation.data

import com.example.typesafecomposenavigation.model.RecipeModel
import com.example.typesafecomposenavigation.model.RecipeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

object RecipeRepository {
    private val _likedRecipes = MutableStateFlow<Set<Int>>(setOf())

    val favoriteRecipes = _likedRecipes.map { likedRecipeIds ->

        SAMPLERECIPES.filter { it.id in likedRecipeIds }
    }


    fun getAllRecipes(): List<RecipeModel> {
        return SAMPLERECIPES
    }

    fun getRecipesByType(recipeType: RecipeType): List<RecipeModel> {
        return when (recipeType) {
            RecipeType.Breakfast -> breakfastRecipes
            RecipeType.Lunch -> lunchRecipes
            RecipeType.Supper -> supperRecipes
            RecipeType.Snack -> snackRecipes
        }


    }


    fun getRecipeById(id: Int): RecipeModel? {
        return SAMPLERECIPES.find { it.id == id }
            ?.copy(isFavorite = _likedRecipes.value.contains(id))
    }


    fun addToFavorites(id: Int) {

        _likedRecipes.value += id
    }

    fun removeFromFavorites(id: Int) {
        _likedRecipes.value -= id
    }


}

