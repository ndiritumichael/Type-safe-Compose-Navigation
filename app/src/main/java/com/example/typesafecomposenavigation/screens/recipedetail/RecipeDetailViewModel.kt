package com.example.typesafecomposenavigation.screens.recipedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.typesafecomposenavigation.ui.navigation.RecipeDestinations
import com.example.typesafecomposenavigation.data.RecipeRepository
import com.example.typesafecomposenavigation.model.RecipeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {


    /**
     *   You can also access the arguments directly from the [SavedStateHandle].
     */
    private val detailRoute: RecipeDestinations.RecipeDetails = savedStateHandle.toRoute()


    private val _recipeDetails = MutableStateFlow<RecipeDetailState>(RecipeDetailState.Idle)

    val recipeDetails = _recipeDetails.asStateFlow()


    fun getRecipeById(id: Int) {


        // In a production app set up your repository as a constructor argument
        RecipeRepository.getRecipeById(id)?.let {

            _recipeDetails.value = RecipeDetailState.Success(it)

        } ?: {
            _recipeDetails.value = RecipeDetailState.Error("Recipe not found")
        }


    }

    fun toggleFavorite(id: Int, isFavorite: Boolean) {

        if (isFavorite) {
            RecipeRepository.removeFromFavorites(id)
        } else {
            RecipeRepository.addToFavorites(id)
        }


    }


}

sealed interface RecipeDetailState {
    data object Idle : RecipeDetailState
    data object Loading : RecipeDetailState
    data class Error(val message: String) : RecipeDetailState
    data class Success(val recipe: RecipeModel) : RecipeDetailState

}