package com.example.typesafecomposenavigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
enum class RecipeType : Parcelable {
    Breakfast,
    Lunch,
    Supper,
    Snack
}


enum class DifficultyLevel{
    Beginner,
    Intermediate,
    Advanced
}
