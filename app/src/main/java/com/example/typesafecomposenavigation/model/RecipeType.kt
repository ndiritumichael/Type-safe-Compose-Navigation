package com.example.typesafecomposenavigation.model

import androidx.annotation.Keep

@Keep
enum class RecipeType {
    Breakfast,
    Lunch,
    Supper,
    Snack,
}

enum class DifficultyLevel {
    Beginner,
    Intermediate,
    Advanced,
}
