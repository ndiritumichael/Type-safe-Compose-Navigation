package com.example.typesafecomposenavigation.model

data class RecipeModel(
    val id: Int,
    val name: String,
    val mealType: RecipeType,
    val difficultyLevel: DifficultyLevel,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val servings: Int,
    val isFavorite: Boolean = false
)
